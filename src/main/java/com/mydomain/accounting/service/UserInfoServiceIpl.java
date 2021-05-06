package com.mydomain.accounting.service;

import com.mydomain.accounting.converter.persistenceToCommon.UserInfoP2CConverter;
import com.mydomain.accounting.dao.UserInfoDaoIpl;
import com.mydomain.accounting.exception.ResourceNotFoundException;
import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.model.persistence.UserInfoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceIpl implements UserInfoService {
    private final UserInfoP2CConverter userInfoP2CConverter;
    private final UserInfoDaoIpl userInfoDaoIpl;

    @Autowired
    public UserInfoServiceIpl(UserInfoP2CConverter userInfoP2CConverter, UserInfoDaoIpl userInfoDaoIpl) {
        this.userInfoP2CConverter = userInfoP2CConverter;
        this.userInfoDaoIpl = userInfoDaoIpl;
    }

    public UserInfoCommon getUserInfoById(int id) {
        UserInfoPersistence userInfoPersistence =
                Optional.ofNullable(userInfoDaoIpl.getUserInfoById(id))
                        .orElseThrow(() -> new ResourceNotFoundException("找不到该用户"));

        return userInfoP2CConverter.convert(userInfoPersistence);
    }
}
