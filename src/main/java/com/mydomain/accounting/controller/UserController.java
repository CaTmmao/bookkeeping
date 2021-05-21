package com.mydomain.accounting.controller;

import java.util.Map;

import com.mydomain.accounting.converter.commonToService.UserInfoC2SConverter;
import com.mydomain.accounting.exception.InvalidParameterException;
import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.model.service.UserInfoServiceModel;
import com.mydomain.accounting.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1.0/users")
public class UserController {
    private final UserInfoService userInfoService;
    private final UserInfoC2SConverter userInfoC2SConverter;

    public UserController(UserInfoService userInfoService, UserInfoC2SConverter userInfoC2SConverter) {
        this.userInfoService = userInfoService;
        this.userInfoC2SConverter = userInfoC2SConverter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoServiceModel> getUserInfo(@PathVariable int id) {
        if (id <= 0) {
            throw new InvalidParameterException(String.format("参数 id 的值应大于 0,当前值为 %s", id));
        }

        UserInfoCommon userInfoCommon = userInfoService.getUserInfoById(id);
        return ResponseEntity.ok(userInfoC2SConverter.convert(userInfoCommon));
    }

    @PostMapping(consumes = "application/json", // 可接收的客户端传来的媒体类型
        produces = "application/json")// 向客户端返回的媒体类型
    public ResponseEntity<UserInfoServiceModel> register(@RequestBody Map<String, String> param) {
        String username = param.get("username");
        String password = param.get("password");
        UserInfoCommon userInfo = userInfoService.register(username, password);
        return ResponseEntity.ok(userInfoC2SConverter.convert(userInfo));
    }
}
