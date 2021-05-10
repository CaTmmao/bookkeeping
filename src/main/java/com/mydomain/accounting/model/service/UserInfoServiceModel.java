package com.mydomain.accounting.model.service;

import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceModel {
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserInfoServiceModel() {}

    public UserInfoServiceModel(String username) {
        this.username = username;
    }
}
