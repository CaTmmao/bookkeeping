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
import org.springframework.stereotype.Service;

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
                    String.format("tagId 为 %s 的标签不属于 userId 为 %s 的用户", tag.getId(), recordUserId));
            });
    }

    @SuppressWarnings("checkstyle:WhitespaceAround")
    @Override
    public RecordCommonModel createRecord(RecordCommonModel record) {
        // 检查当前用户是否可以使用这些标签
        List<Long> tagIdList = getTagIdList(record);
        List<TagPersistenceModel> tagList = getTagListByTagIdList(tagIdList);
        checkTagIfValid(tagList, record);

        RecordPersistenceModel newRecord = recordP2CConverter.reverse().convert(record);

        /*
         * 需要用事务来执行下面的语句
         * 原因：当 insertRecord 执行成功后，batchInsertRecordIdTagIdMapping 如果失败了，
         * 为了避免在数据库残留多余数据，需要撤销 insertRecord 的执行
         */
        recordDao.insertRecord(newRecord);
        assert newRecord != null;
        recordIdTagMappingDao.batchInsertRecordIdTagIdMapping(tagList, newRecord.getId());
        return getRecordByRecordId(newRecord.getId());
    }

    @Override
    public RecordCommonModel getRecordByRecordId(Long recordId) {
        return Optional.ofNullable(recordDao.getRecordByRecordId(recordId))
            .map(recordP2CConverter::convert)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("找不到 id 为 %s 的记账记录", recordId)));
    }

    public List<TagPersistenceModel> getTagListByTagIdList(List<Long> tagIdList) {
        List<TagPersistenceModel> list = tagDao.getTagListByTagIdList(tagIdList);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException(String.format("找不到 id 为 %s 的标签", tagIdList));
        }
        return list;
    }

    @Override
    public RecordCommonModel updateRecord(RecordCommonModel record) {
        Long recordId = record.getId();
        RecordPersistenceModel recordFromDB = Optional.ofNullable(recordDao.getRecordByRecordId(recordId))
            .orElseThrow(() -> new ResourceNotFoundException(String.format("找不到 id 为 %s 的记账记录", recordId)));

        Long recordUserId = record.getUserId();
        if (!recordFromDB.getUserId().equals(recordUserId)) {
            throw new InvalidParameterException(
                String.format("id 为 %s 的记账记录不属于 userId 为 %s 的用户，无法更新", recordId, recordUserId));
        }

        RecordPersistenceModel updateRecord = recordP2CConverter.reverse().convert(record);
        assert updateRecord != null;

        // 检查标签是否更改
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
