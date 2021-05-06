package com.mydomain.accounting.converter.persistenceToCommon;

import com.google.common.base.Converter;
import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.model.common.UserInfoCommonBuilder;
import com.mydomain.accounting.model.persistence.UserInfoPersistence;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

@Service
public class UserInfoP2CConverter extends Converter<UserInfoPersistence, UserInfoCommon> {
    @Override
    protected UserInfoCommon doForward(UserInfoPersistence userInfoPersistence) {
        int id = userInfoPersistence.getId();
        String name = userInfoPersistence.getUsername();
        String pwd = userInfoPersistence.getPassword();

        return new UserInfoCommonBuilder()
                .setId(id)
                .setUsername(name)
                .setPassword(pwd)
                .createUserInfo();
    }

    @Override
    protected UserInfoPersistence doBackward(UserInfoCommon userInfoCommon) {
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
