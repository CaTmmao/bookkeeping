package com.mydomain.accounting.converter.persistenceToCommon;

import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.model.common.UserInfoCommonBuilder;
import com.mydomain.accounting.model.persistence.UserInfoPersistence;
import com.mydomain.accounting.model.persistence.UserInfoPersistenceBuilder;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoP2CConverterTest {
    UserInfoP2CConverter userInfoP2CConverter = new UserInfoP2CConverter();

    @Test
    void testDoForward() {
        // arrange
        UserInfoPersistence userInfoPersistence = new UserInfoPersistenceBuilder()
                .setUsername("name")
                .setPassword("pwd")
                .setId(1)
                .setCreateTime(Instant.now())
                .setUpdateTime(Instant.now())
                .createUserInfo();

        // act
        UserInfoCommon userInfoCommon = userInfoP2CConverter.convert(userInfoPersistence);

        // assert
        assertEquals("com.mydomain.accounting.model.common.UserInfoCommon", userInfoCommon.getClass().getName());
        assertEquals("name", userInfoCommon.getUsername());
        assertEquals("pwd", userInfoCommon.getPassword());
        assertEquals(1, userInfoCommon.getId());
    }

    @Test
    void testBackward() {
        // arrange
        UserInfoCommon userInfoCommon = new UserInfoCommonBuilder()
                .setPassword("pwd")
                .setUsername("name")
                .setId(1)
                .createUserInfo();

        // act && assert
        assertThrows(UnsupportedOperationException.class, () -> userInfoP2CConverter.reverse().convert(userInfoCommon));
    }

}