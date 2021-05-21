package com.mydomain.accounting.dao;

import com.mydomain.accounting.dao.mapper.RecordMapper;
import com.mydomain.accounting.model.persistence.RecordPersistenceModel;
import org.springframework.stereotype.Repository;

@Repository
public class RecordDaoIpl implements RecordDao {
    private final RecordMapper recordMapper;

    public RecordDaoIpl(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    @Override
    public void insertRecord(RecordPersistenceModel record) {
        record.setStatus(1);
        recordMapper.insertRecord(record);
    }

    @Override
    public RecordPersistenceModel getRecordByRecordId(Long id) {
        return recordMapper.getRecordByRecordId(id);
    }

    @Override
    public void updateRecord(RecordPersistenceModel record) {
        recordMapper.updateRecord(record);
    }
}
