package com.mydomain.accounting.dao.provider;

import com.mydomain.accounting.model.persistence.RecordPersistenceModel;
import org.apache.ibatis.jdbc.SQL;

public class RecordSqlProvider {
    public String updateRecord(final RecordPersistenceModel record) {
        return new SQL() {
            {
                UPDATE("hcas_record");
                if (record.getAmount() != null) {
                    SET("amount = #{amount}");
                }
                if (record.getCategory() != null) {
                    SET("category = #{category}");
                }
                if (record.getNote() != null) {
                    SET("note = #{note}");
                }
                if (record.getStatus() != null) {
                    SET("status = #{status}");
                }
                if (record.getUserId() != null) {
                    SET("user_id = #{userId}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
