package com.mydomain.accounting.dao;

import com.mydomain.accounting.dao.manager.UserInfoMapper;
import com.mydomain.accounting.model.persistence.UserInfoPersistence;
import com.mydomain.accounting.model.persistence.UserInfoPersistenceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserInfoDAOIplTest {
    @Mock
    UserInfoMapper userInfoMapper;

    @InjectMocks
    UserInfoDAOIpl userInfoDAOIpl ;

    @Test
    void testGetUserInfoById() {
        // arrange
        int id = 1;
        UserInfoPersistence userInfoPersistence = new UserInfoPersistenceBuilder()
                .setUsername("name")
                .setId(id)
                .setPassword("pwd")
                .setCreateTime(Instant.now())
                .setUpdateTime(Instant.now())
                .createUserInfo();

        when(userInfoMapper.getUserInfoById(1)).thenReturn(userInfoPersistence);

        // act
        UserInfoPersistence result = userInfoDAOIpl.getUserInfoById(id);

        // assert
        assertEquals(userInfoPersistence, result);
        verify(userInfoMapper).getUserInfoById(id);
    }
}