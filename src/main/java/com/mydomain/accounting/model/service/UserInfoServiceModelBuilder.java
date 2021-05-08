package com.mydomain.accounting.model.service;

public class UserInfoServiceModelBuilder {
    private String username;

    public UserInfoServiceModelBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserInfoServiceModel createUserInfo() {
        return new UserInfoServiceModel(username);
    }
}