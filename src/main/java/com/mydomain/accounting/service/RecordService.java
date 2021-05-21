package com.mydomain.accounting.service;

import com.mydomain.accounting.model.common.RecordCommonModel;

public interface RecordService {
    RecordCommonModel createRecord(RecordCommonModel record);
    RecordCommonModel getRecordByRecordId(Long recordId);
    RecordCommonModel updateRecord(RecordCommonModel record);
}
