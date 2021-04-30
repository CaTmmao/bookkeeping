package com.mydomain.accounting.model.common;

import org.springframework.stereotype.Service;

@Service("UserInfoCommon")
public class UserInfoCommon {
    int id;
    String username;
    String password;

    public UserInfoCommon() {}

    public UserInfoCommon(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
