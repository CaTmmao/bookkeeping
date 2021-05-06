package com.mydomain.accounting.service;

import java.util.Optional;

import com.mydomain.accounting.converter.persistenceToCommon.UserInfoP2CConverter;
import com.mydomain.accounting.dao.UserInfoDaoIpl;
import com.mydomain.accounting.exception.ResourceNotFoundException;
import com.mydomain.accounting.model.common.UserInfoCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return Optional.ofNullable(userInfoDaoIpl.getUserInfoById(id))
            .map(userInfoP2CConverter::convert)
            .orElseThrow(() -> new ResourceNotFoundException("找不到该用户"));
    }
}
