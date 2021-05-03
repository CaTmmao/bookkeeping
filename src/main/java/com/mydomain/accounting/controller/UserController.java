package com.mydomain.accounting.controller;

import com.mydomain.accounting.converter.commonToService.UserInfoC2SConverter;
import com.mydomain.accounting.exception.InvalidParameterException;
import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.model.service.UserInfoService;
import com.mydomain.accounting.service.UserInfoServiceIpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserInfoServiceIpl userInfoServiceIpl;
    private final UserInfoC2SConverter userInfoC2SConverter;

    public UserController(UserInfoServiceIpl userInfoServiceIpl, UserInfoC2SConverter userInfoC2SConverter) {
        this.userInfoServiceIpl = userInfoServiceIpl;
        this.userInfoC2SConverter = userInfoC2SConverter;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserInfoService> getUserInfo(@PathVariable int id) {
        if (id <= 0) {
            throw new InvalidParameterException("参数错误");
        }

        UserInfoCommon userInfoCommon = userInfoServiceIpl.getUserInfoById(id);
        return ResponseEntity.ok(userInfoC2SConverter.convert(userInfoCommon));
    }
}
