package com.mydomain.accounting.service;

import com.mydomain.accounting.converter.persistenceToCommon.UserInfoP2CConverter;
import com.mydomain.accounting.dao.UserDao;
import com.mydomain.accounting.exception.ResourceNotFoundException;
import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.model.persistence.UserInfoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceIpl implements UserInfoService {
    private final UserInfoP2CConverter userInfoP2CConverter;
    private final UserDao userDao;

    @Autowired
    public UserInfoServiceIpl(UserInfoP2CConverter userInfoP2CConverter, UserDao userDao) {
        this.userInfoP2CConverter = userInfoP2CConverter;
        this.userDao = userDao;
    }

    public UserInfoCommon getUserInfoById(int id) {
        UserInfoPersistence userInfoPersistence =
                Optional.ofNullable(userDao.getUserInfoById(id))
                        .orElseThrow(() -> new ResourceNotFoundException("找不到该用户"));

        return userInfoP2CConverter.convert(userInfoPersistence);
    }
}
