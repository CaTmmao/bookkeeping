package com.mydomain.accounting.converter.commonToService;

import com.google.common.base.Converter;
import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.model.service.UserInfoServiceBuilder;
import com.mydomain.accounting.model.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoC2SConverter extends Converter<UserInfoCommon, UserInfoService> {
    @Override
    protected UserInfoService doForward(UserInfoCommon userInfoCommon) {
        String name = userInfoCommon.getUsername();
        return new UserInfoServiceBuilder().setUsername(name).createUserInfo();
    }

    @Override
    protected UserInfoCommon doBackward(UserInfoService userInfoService) {
        throw new UnsupportedOperationException();
    }
}
