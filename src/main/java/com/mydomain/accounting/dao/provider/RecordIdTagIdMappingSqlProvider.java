package com.mydomain.accounting.dao.provider;

import java.util.List;

import com.mydomain.accounting.model.persistence.RecordIdTagIdMappingPersistenceModel;
import org.apache.ibatis.jdbc.SQL;

public class RecordIdTagIdMappingSqlProvider {
    public String batchRecordIdTagIdMapping(final List<RecordIdTagIdMappingPersistenceModel> list) {
        return new SQL() {
            {
                INSERT_INTO("hcas_record_id_tag_id_mapping");
                INTO_COLUMNS("record_id", "tag_id", "status");
                for (RecordIdTagIdMappingPersistenceModel item : list) {
                    Long recordId = item.getRecordId();
                    Long tagId = item.getTagId();
                    Integer status = item.getStatus();
                    INTO_VALUES(String.format("%s, %s, %s", recordId, tagId, status));
                    ADD_ROW();
                }
            }
        }
            .toString();
    }
}
