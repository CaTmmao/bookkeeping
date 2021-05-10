package com.mydomain.accounting.model.common;

public class UserInfoCommonBuilder {
    private int id;
    private String username;
    private String password;
    private String salt;

    public UserInfoCommonBuilder setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public UserInfoCommonBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserInfoCommonBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserInfoCommonBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserInfoCommon createUserInfo() {
        return new UserInfoCommon(id, username, password, salt);
    }
}