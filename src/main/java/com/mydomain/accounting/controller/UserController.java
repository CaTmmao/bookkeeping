package com.mydomain.accounting.controller;

import com.mydomain.accounting.converter.commonToService.UserInfoC2SConverter;
import com.mydomain.accounting.exception.InvalidParameterException;
import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1.0/users")
public class UserController {
    //    private final UserInfoServiceIpl userInfoServiceIpl;
    private final UserInfoService userInfoService;
    private final UserInfoC2SConverter userInfoC2SConverter;

    public UserController(UserInfoService userInfoService, UserInfoC2SConverter userInfoC2SConverter) {
        this.userInfoService = userInfoService;
        this.userInfoC2SConverter = userInfoC2SConverter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.mydomain.accounting.model.service.UserInfoService> getUserInfo(@PathVariable int id) {
        if (id <= 0) {
            throw new InvalidParameterException("参数错误");
        }

        UserInfoCommon userInfoCommon = userInfoService.getUserInfoById(id);
        return ResponseEntity.ok(userInfoC2SConverter.convert(userInfoCommon));
    }
}
