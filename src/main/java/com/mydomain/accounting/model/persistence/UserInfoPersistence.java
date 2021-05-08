package com.mydomain.accounting.model.persistence;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service("UserInfoPersistence")
public class UserInfoPersistence {
    private int id;
    private String username;
    private String password;
    private Instant createTime;
    private Instant updateTime;
    private String salt;

    public UserInfoPersistence() {}

    public UserInfoPersistence(int id, String username, String password, Instant createTime, Instant updateTime,
                               String salt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.salt = salt;
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

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
