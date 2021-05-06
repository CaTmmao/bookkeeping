package com.mydomain.accounting.dao;

import com.mydomain.accounting.dao.manager.UserInfoMapper;
import com.mydomain.accounting.model.persistence.UserInfoPersistence;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDaoIpl implements UserInfoDao {
    private final UserInfoMapper userInfoMapper;

    public UserInfoDaoIpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    public UserInfoPersistence getUserInfoById(int id) {
        return userInfoMapper.getUserInfoById(id);
    }
}
