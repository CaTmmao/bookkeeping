package com.mydomain.accounting.service;

import com.mydomain.accounting.model.common.UserInfoCommon;

public interface UserInfoService {
    UserInfoCommon getUserInfoById(int id);

    UserInfoCommon getUserInfoByUsername(String username);

    void login(String username, String password);

}
