package com.mydomain.accounting.controller;

import com.mydomain.accounting.service.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1.0/session")
public class SessionController {
    private final UserInfoService userInfoService;

    public SessionController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        userInfoService.login(username, password);
        return "success";
    }
}
