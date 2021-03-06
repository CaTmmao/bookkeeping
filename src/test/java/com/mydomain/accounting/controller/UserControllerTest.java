package com.mydomain.accounting.controller;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mydomain.accounting.converter.commonToService.UserInfoC2SConverter;
import com.mydomain.accounting.exception.GlobalExceptionHandler;
import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.model.common.UserInfoCommonBuilder;
import com.mydomain.accounting.model.service.UserInfoServiceModel;
import com.mydomain.accounting.model.service.UserInfoServiceModelBuilder;
import com.mydomain.accounting.service.UserInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 测试 controller 时和测试其他的类不一样，不适合用 new XXController 实例化该类
 * 因为他这样无法测出来 MVC 的一些特性，如返回 Exception 时被 ExceptionHandler 抓住，new 出来的 controller 做不到
 * 我们通过使用 MockMvc 类实例化 controller 可以做到这点
 */

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    UserInfoService userInfoService;

    @Mock
    UserInfoC2SConverter userInfoC2SConverter;

    MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
            .standaloneSetup(new UserController(userInfoService, userInfoC2SConverter))
            .setControllerAdvice(new GlobalExceptionHandler()) // 控制抛出的异常
            .build();
    }

    @Test
    void testGetUserInfoById() throws Exception {
        // arrange
        int id = 1;

        UserInfoCommon userInfoCommon = new UserInfoCommonBuilder()
            .setId(id)
            .setUsername("name")
            .setPassword("pwd")
            .createUserInfo();

        UserInfoServiceModel userInfo = new UserInfoServiceModelBuilder()
            .setUsername("name")
            .createUserInfo();

        String responseString = new ObjectMapper().writeValueAsString(userInfo);

        when(userInfoService.getUserInfoById(id)).thenReturn(userInfoCommon);
        when(userInfoC2SConverter.convert(userInfoCommon)).thenReturn(userInfo);

        // act
        mvc.perform(get("/v1.0/users/" + id).contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(content().string(responseString));

        // assert
        verify(userInfoService).getUserInfoById(id);
        verify(userInfoC2SConverter).convert(userInfoCommon);
    }

    @Test
    void testGetUserInfoByIdWithInvalidId() throws Exception {
        // arrange
        int id = 0;

        // act
        mvc.perform(get("/user/" + id))
            .andExpect(status().is4xxClientError());

        // assert
        verify(userInfoService, never()).getUserInfoById(id);
    }
}