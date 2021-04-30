package com.mydomain.accounting.model.service;

public class UserInfoServiceBuilder {
    private String username;

    public UserInfoServiceBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserInfoService createUserInfo() {
        return new UserInfoService(username);
    }
}