package com.mydomain.accounting.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mydomain.accounting.converter.persistenceToCommon.RecordP2CConverter;
import com.mydomain.accounting.dao.RecordDao;
import com.mydomain.accounting.dao.RecordIdTagIdMappingDao;
import com.mydomain.accounting.dao.TagDao;
import com.mydomain.accounting.exception.InvalidParameterException;
import com.mydomain.accounting.exception.ResourceNotFoundException;
import com.mydomain.accounting.model.common.RecordCommonModel;
import com.mydomain.accounting.model.common.TagCommonModel;
import com.mydomain.accounting.model.persistence.RecordPersistenceModel;
import com.mydomain.accounting.model.persistence.TagPersistenceModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecordServiceIpl implements RecordService {
    private final RecordDao recordDao;
    private final RecordP2CConverter recordP2CConverter;
    private final TagDao tagDao;
    private final RecordIdTagIdMappingDao recordIdTagMappingDao;

    public RecordServiceIpl(RecordDao recordDao, RecordP2CConverter recordP2CConverter,
                            TagDao tagDao, RecordIdTagIdMappingDao recordIdTagMappingDao) {
        this.recordDao = recordDao;
        this.recordP2CConverter = recordP2CConverter;
        this.tagDao = tagDao;
        this.recordIdTagMappingDao = recordIdTagMappingDao;
    }

    public List<Long> getTagIdList(RecordCommonModel record) {
        return record.getTagList()
            .stream()
            .map(TagCommonModel::getId)
            .collect(Collectors.toList());
    }

    public void checkTagIfValid(List<TagPersistenceModel> tagList, RecordCommonModel record) {
        Long recordUserId = record.getUserId();

        tagList.stream()
            .filter(tag -> !tag.getUserId().equals(recordUserId))
            .findAny()
            .ifPresent(tag -> {
                throw new InvalidParameterException(
                    String.format("tagId ??? %s ?????????????????? userId ??? %s ?????????", tag.getId(), recordUserId));
            });
    }

    @SuppressWarnings("checkstyle:WhitespaceAround")
    @Override
    @Transactional
    public RecordCommonModel createRecord(RecordCommonModel record) {
        // ????????????????????????????????????????????????
        List<Long> tagIdList = getTagIdList(record);
        List<TagPersistenceModel> tagList = getTagListByTagIdList(tagIdList);
        checkTagIfValid(tagList, record);

        RecordPersistenceModel newRecord = recordP2CConverter.reverse().convert(record);

        /*
         * ?????????????????????????????????
         * ???????????? insertRecord ??????????????????batchInsertRecordIdTagIdMapping ??????????????????
         * ????????????????????????????????????????????????????????? insertRecord ?????????
         */
        recordDao.insertRecord(newRecord);
        assert newRecord != null;
        recordIdTagMappingDao.batchInsertRecordIdTagIdMapping(tagList, newRecord.getId());
        return getRecordByRecordId(newRecord.getId());
    }

    @Override
    @Cacheable(value = "record", key = "#recordId")
    public RecordCommonModel getRecordByRecordId(Long recordId) {
        return Optional.ofNullable(recordDao.getRecordByRecordId(recordId))
            .map(recordP2CConverter::convert)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("????????? id ??? %s ???????????????", recordId)));
    }

    public List<TagPersistenceModel> getTagListByTagIdList(List<Long> tagIdList) {
        List<TagPersistenceModel> list = tagDao.getTagListByTagIdList(tagIdList);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException(String.format("????????? id ??? %s ?????????", tagIdList));
        }
        return list;
    }

    @CacheEvict(value = "record", key = "#record.id")
    @Override
    public RecordCommonModel updateRecord(RecordCommonModel record) {
        Long recordId = record.getId();
        RecordPersistenceModel recordFromDB = Optional.ofNullable(recordDao.getRecordByRecordId(recordId))
            .orElseThrow(() -> new ResourceNotFoundException(String.format("????????? id ??? %s ???????????????", recordId)));

        Long recordUserId = record.getUserId();
        if (!recordFromDB.getUserId().equals(recordUserId)) {
            throw new InvalidParameterException(
                String.format("id ??? %s ???????????????????????? userId ??? %s ????????????????????????", recordId, recordUserId));
        }

        RecordPersistenceModel updateRecord = recordP2CConverter.reverse().convert(record);
        assert updateRecord != null;

        // ????????????????????????
        if (!record.getTagList().isEmpty() && !recordFromDB.getTagList().equals(updateRecord.getTagList())) {
            List<Long> tagIdList = getTagIdList(record);
            List<TagPersistenceModel> tagList = getTagListByTagIdList(tagIdList);
            checkTagIfValid(tagList, record);

            recordIdTagMappingDao.deleteRecordIdTagIdMappingListByRecordId(record.getId());
            recordIdTagMappingDao.batchInsertRecordIdTagIdMapping(tagList, record.getId());
        }

        recordDao.updateRecord(updateRecord);
        return getRecordByRecordId(record.getId());
    }
}
