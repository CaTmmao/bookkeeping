package com.mydomain.accounting.dao.mapper;

import java.util.List;

import com.mydomain.accounting.dao.provider.RecordSqlProvider;
import com.mydomain.accounting.model.persistence.RecordPersistenceModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface RecordMapper {
    /**
     * 插入一条记账记录
     *
     * @param record record 对象
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO hcas_record(user_id, amount, note, category, status)"
        + "VALUES (#{userId}, #{amount}, #{note}, #{category}, #{status})")
    void insertRecord(RecordPersistenceModel record);

    /**
     * 通过 id 获取记录信息
     *
     * @param id 记录 id
     * @return 记录信息
     */
    @Select("SELECT id, status, note, category, user_id, amount FROM hcas_record"
        + " WHERE id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "tagList", javaType = List.class, column = "id",
            many = @Many(select = "com.mydomain.accounting.dao.mapper."
                + "RecordIdTagIdMappingMapper.getTagListByRecordId"))})
    RecordPersistenceModel getRecordByRecordId(Long id);

    /**
     * 更新记录
     *
     * @param record record 对象
     */
    @UpdateProvider(type = RecordSqlProvider.class, method = "updateRecord")
    void updateRecord(RecordPersistenceModel record);
}
