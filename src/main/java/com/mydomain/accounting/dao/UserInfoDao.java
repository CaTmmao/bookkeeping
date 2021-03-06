package com.mydomain.accounting.dao;

import com.mydomain.accounting.model.persistence.UserInfoPersistence;

public interface UserInfoDao {
    UserInfoPersistence getUserInfoById(int id);
    UserInfoPersistence getUserInfoByUsername(String username);
    void createNewUser(UserInfoPersistence userInfo);
}
