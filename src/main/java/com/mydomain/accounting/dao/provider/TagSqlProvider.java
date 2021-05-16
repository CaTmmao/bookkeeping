package com.mydomain.accounting.dao.provider;

import com.mydomain.accounting.model.persistence.TagPersistenceModel;
import org.apache.ibatis.jdbc.SQL;

public class TagSqlProvider {
    public String updateTag(final TagPersistenceModel tag) {
        return new SQL() {
            {
                UPDATE("hcas_tag");
                if (tag.getDescription() != null) {
                    SET("description = #{description}");
                }
                if (tag.getStatus() != null) {
                    SET("status = #{status}");
                }
                SET("update_time = now()");
                WHERE("id = #{id}");
            }
        }
            .toString();
    }
}
