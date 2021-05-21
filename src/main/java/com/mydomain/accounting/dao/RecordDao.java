package com.mydomain.accounting.dao;

import com.mydomain.accounting.model.persistence.RecordPersistenceModel;

public interface RecordDao {
    void insertRecord(RecordPersistenceModel id);
    RecordPersistenceModel getRecordByRecordId(Long id);
    void updateRecord(RecordPersistenceModel record);
}
