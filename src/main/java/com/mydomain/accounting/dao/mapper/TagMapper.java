package com.mydomain.accounting.dao.mapper;

import java.util.List;

import com.mydomain.accounting.dao.provider.TagSqlProvider;
import com.mydomain.accounting.model.persistence.TagPersistenceModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface TagMapper {
    @SelectProvider(type = TagSqlProvider.class, method = "getTagListByTagIdList")
    List<TagPersistenceModel> getTagListByTagIdList(List<Long> tagIdList);

    /**
     * 创建标签
     * @param tag 标签对象（persistence 模型）
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT into hcas_tag(description, status, user_id) "
        + "values(#{description}, #{status}, #{userId})")
    void createTag(TagPersistenceModel tag);

    /**
     * 通过标签描述获取标签信息
     * @param description 标签描述
     * @return 标签对象
     */
    @Select("SELECT id, status, description, user_id, create_time, update_time from hcas_tag "
        + "where description = #{description}")
    TagPersistenceModel getTagByTagDescription(@Param("description") String description);

    /**
     * 通过标签 id 获取标签信息
     * @param id 标签 id
     * @return 标签对象
     */
    @Select("SELECT id, status, description, user_id, create_time, update_time from hcas_tag "
        + "where id = #{id}")
    TagPersistenceModel getTagByTagId(@Param("id") Long id);

    /**
     * 通过 userId 获取标签列表
     * @param userId   用户 id
     * @param pageNum  第几页
     * @param pageSize 每页几条数据
     * @return 标签列表
     */
    @Select("SELECT id, status, description, user_id, create_time, update_time from hcas_tag"
        + " WHERE user_id = #{userId}")
    List<TagPersistenceModel> getTagListByUserId(@Param("userId") Long userId,
                                                 @Param("pageNum") int pageNum,
                                                 @Param("pageSize") int pageSize);

    /**
     * 更新标签信息
     * @param tag 标签对象
     */
    @UpdateProvider(type = TagSqlProvider.class, method = "updateTag")
    void updateTag(TagPersistenceModel tag);
}
