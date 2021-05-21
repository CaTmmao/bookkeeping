package com.mydomain.accounting.dao;

import java.util.List;

import com.mydomain.accounting.model.persistence.TagPersistenceModel;

public interface RecordIdTagIdMappingDao {
    void batchInsertRecordIdTagIdMapping(List<TagPersistenceModel> tagList, Long recordId);
    void deleteRecordIdTagIdMappingListByRecordId(Long id);
}
