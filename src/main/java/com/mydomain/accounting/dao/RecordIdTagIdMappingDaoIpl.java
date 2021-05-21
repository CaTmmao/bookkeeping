package com.mydomain.accounting.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.mydomain.accounting.dao.mapper.RecordIdTagIdMappingMapper;
import com.mydomain.accounting.model.persistence.RecordIdTagIdMappingPersistenceModel;
import com.mydomain.accounting.model.persistence.RecordIdTagIdMappingPersistenceModelBuilder;
import com.mydomain.accounting.model.persistence.TagPersistenceModel;
import org.springframework.stereotype.Repository;

@Repository
public class RecordIdTagIdMappingDaoIpl implements RecordIdTagIdMappingDao {
    private final RecordIdTagIdMappingMapper recordIdTagIdMappingMapper;

    public RecordIdTagIdMappingDaoIpl(
        RecordIdTagIdMappingMapper recordIdTagIdMappingMapper) {
        this.recordIdTagIdMappingMapper = recordIdTagIdMappingMapper;
    }

    @Override
    public void batchInsertRecordIdTagIdMapping(List<TagPersistenceModel> tagList, Long recordId) {
        List<RecordIdTagIdMappingPersistenceModel> recordIdTagIdMappingList = tagList
            .stream()
            .map(tag -> RecordIdTagIdMappingPersistenceModelBuilder.builder()
                .tagId(tag.getId())
                .recordId(recordId)
                .status(1)
                .build())
            .collect(Collectors.toList());

        recordIdTagIdMappingMapper.batchRecordIdTagIdMapping(recordIdTagIdMappingList);
    }

    @Override
    public void deleteRecordIdTagIdMappingListByRecordId(Long id) {
        recordIdTagIdMappingMapper.deleteRecordIdTagIdMappingListByRecordId(id);
    }
}
