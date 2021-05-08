package com.mydomain.accounting.converter.commonToService;

import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.model.common.UserInfoCommonBuilder;
import com.mydomain.accounting.model.service.UserInfoServiceModel;
import com.mydomain.accounting.model.service.UserInfoServiceModelBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoC2SConverterTest {
    UserInfoC2SConverter userInfoC2SConverter = new UserInfoC2SConverter();

    @Test
    void testDoForward() {
        // arrange
        UserInfoCommon userInfoCommon = new UserInfoCommonBuilder()
                .setId(1)
                .setUsername("name")
                .setPassword("pwd")
                .createUserInfo();

        // avt
        UserInfoServiceModel userInfoServiceModel = userInfoC2SConverter.doForward(userInfoCommon);

        // assert
        assertEquals("com.mydomain.accounting.model.service.UserInfoServiceModel", userInfoServiceModel.getClass().getName());
        assertEquals("name", userInfoServiceModel.getUsername());
    }

    @Test
    void testDoBackWard() {
        // arrange
        UserInfoServiceModel userInfoServiceModel = new UserInfoServiceModelBuilder()
                .setUsername("mame")
                .createUserInfo();

        // act && assert
        assertThrows(UnsupportedOperationException.class, () -> userInfoC2SConverter.reverse().convert(
            userInfoServiceModel));
    }


}