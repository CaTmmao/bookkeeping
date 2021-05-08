package com.mydomain.accounting.dao.manager;

import com.mydomain.accounting.model.persistence.UserInfoPersistence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {
    @Select("SELECT * FROM hcas_userinfo WHERE ID = #{id}")
    UserInfoPersistence getUserInfoById(@Param("id") int id);

    @Select("SELECT * FROM hcas_userinfo WHERE username = #{username}")
    UserInfoPersistence getUserInfoByUsername(@Param("username") String username);
}
