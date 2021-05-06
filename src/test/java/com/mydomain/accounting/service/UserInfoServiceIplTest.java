package com.mydomain.accounting.service;

import com.mydomain.accounting.converter.persistenceToCommon.UserInfoP2CConverter;
import com.mydomain.accounting.dao.UserInfoDaoIpl;
import com.mydomain.accounting.dao.manager.UserInfoMapper;
import com.mydomain.accounting.exception.ResourceNotFoundException;
import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.model.persistence.UserInfoPersistence;
import com.mydomain.accounting.model.persistence.UserInfoPersistenceBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserInfoServiceIplTest {
    @Mock
    UserInfoMapper userInfoMapper;

    @Mock
    private UserInfoDaoIpl userInfoDAOIpl;
    private UserInfoServiceIpl userInfoServiceIpl;

    @BeforeEach
    void setup() {
        userInfoServiceIpl = new UserInfoServiceIpl(new UserInfoP2CConverter(), userInfoDAOIpl);
    }

    @Test
    void TestUetUserInfoById() {
        // arrange
        int id = 1;
        UserInfoPersistence userInfoPersistence = new UserInfoPersistenceBuilder()
                .setId(id)
                .setPassword("pwd")
                .setUsername("user")
                .setCreateTime(Instant.now())
                .setUpdateTime(Instant.now())
                .createUserInfo();

        when(userInfoDAOIpl.getUserInfoById(id)).thenReturn(userInfoPersistence);

        // act
        UserInfoCommon userInfoCommon = userInfoServiceIpl.getUserInfoById(id);

        // assert
        assertEquals(1, userInfoCommon.getId());
        assertEquals("pwd", userInfoCommon.getPassword());
        assertEquals("user", userInfoCommon.getUsername());
        verify(userInfoDAOIpl).getUserInfoById(id);
    }

    @Test
    void testGetUserInfoByIdWithInvalidId() {
        // arrange
        int id = -1;
        when(userInfoDAOIpl.getUserInfoById(-1)).thenReturn(null);

        // act & assert
        assertThrows(ResourceNotFoundException.class, () -> userInfoServiceIpl.getUserInfoById(id));
        verify(userInfoDAOIpl).getUserInfoById(id);
    }

}