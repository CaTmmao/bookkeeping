package com.mydomain.accounting.model.service;

import org.springframework.stereotype.Service;

@Service("UserInfoService")
public class UserInfoService {
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserInfoService() {}

    public UserInfoService(String username) {
        this.username = username;
    }
}
