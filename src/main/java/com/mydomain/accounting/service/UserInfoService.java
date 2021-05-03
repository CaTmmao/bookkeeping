package com.mydomain.accounting.service;

import com.mydomain.accounting.model.common.UserInfoCommon;

public interface UserInfoService {
    UserInfoCommon getUserInfoById(int id);
}
