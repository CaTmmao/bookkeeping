package com.mydomain.accounting.dao.mapper;

import java.util.List;

import com.mydomain.accounting.dao.provider.RecordIdTagIdMappingSqlProvider;
import com.mydomain.accounting.model.persistence.RecordIdTagIdMappingPersistenceModel;
import com.mydomain.accounting.model.persistence.TagPersistenceModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RecordIdTagIdMappingMapper {
    /**
     * 同时插入多条数据
     * @param list 待插入的信息数组
     */
    @InsertProvider(type = RecordIdTagIdMappingSqlProvider.class, method = "batchRecordIdTagIdMapping")
    void batchRecordIdTagIdMapping(List<RecordIdTagIdMappingPersistenceModel> list);

    /**
     * 通过 record id 删除所有记录
     * @param id record id
     */
    @Delete("DELETE FROM hcas_record_id_tag_id_mapping WHERE record_id = #{id}")
    void deleteRecordIdTagIdMappingListByRecordId(@Param("id") Long id);

    @Select("SELECT ht.id, ht.description, ht.status, ht.user_id FROM hcas_tag ht "
        + "LEFT JOIN hcas_record_id_tag_id_mapping hrtm "
        + "ON hrtm.tag_id = ht.id "
        + "WHERE hrtm.record_id = #{recordId};")
    List<TagPersistenceModel> getTagListByRecordId(Long recordId);
}
