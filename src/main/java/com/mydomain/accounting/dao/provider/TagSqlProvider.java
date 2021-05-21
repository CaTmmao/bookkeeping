package com.mydomain.accounting.dao.provider;

import java.util.List;

import com.google.common.base.Joiner;
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

    /**
     * 获得标签列表
     *
     * @param tagIdList 标签 id 列表
     * @return 需要执行的 sql 字符串
     */
    public String getTagListByTagIdList(List<Long> tagIdList) {
        return new SQL() {
            {
                SELECT("id", "description", "user_id", "status");
                FROM("hcas_tag");
                WHERE(String.format("id in ('%s')", Joiner.on("','").skipNulls().join(tagIdList)));
            }
        }
            .toString();
    }
}
