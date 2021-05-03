package com.mydomain.accounting.dao;

import com.mydomain.accounting.model.persistence.UserInfoPersistence;

public interface UserInfoDAO {
    UserInfoPersistence getUserInfoById(int id);
}
