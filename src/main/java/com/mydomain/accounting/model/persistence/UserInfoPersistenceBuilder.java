package com.mydomain.accounting.model.persistence;

import java.time.Instant;

public class UserInfoPersistenceBuilder {
    private int id;
    private String username;
    private String password;
    private Instant createTime;
    private Instant updateTime;

    public UserInfoPersistenceBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserInfoPersistenceBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserInfoPersistenceBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserInfoPersistenceBuilder setCreateTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public UserInfoPersistenceBuilder setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public UserInfoPersistence createUserInfo() {
        return new UserInfoPersistence(id, username, password, createTime, updateTime);
    }
}