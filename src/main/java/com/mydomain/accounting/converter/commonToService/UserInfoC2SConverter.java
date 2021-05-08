package com.mydomain.accounting.converter.commonToService;

import com.google.common.base.Converter;
import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.model.service.UserInfoServiceModel;
import com.mydomain.accounting.model.service.UserInfoServiceModelBuilder;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

@Service
public class UserInfoC2SConverter extends Converter<UserInfoCommon, UserInfoServiceModel> {
    @Override
    protected UserInfoServiceModel doForward(UserInfoCommon userInfoCommon) {
        String name = userInfoCommon.getUsername();
        return new UserInfoServiceModelBuilder()
            .setUsername(name)
            .createUserInfo();
    }

    @Override
    protected UserInfoCommon doBackward(UserInfoServiceModel userInfoServiceModel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return super.equals(object);
    }
}
