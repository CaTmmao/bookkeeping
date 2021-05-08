package com.mydomain.accounting.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.mydomain.accounting.converter.persistenceToCommon.UserInfoP2CConverter;
import com.mydomain.accounting.dao.UserInfoDao;
import com.mydomain.accounting.exception.InvalidParameterException;
import com.mydomain.accounting.exception.ResourceNotFoundException;
import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.model.persistence.UserInfoPersistence;
import com.mydomain.accounting.model.persistence.UserInfoPersistenceBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceIpl implements UserInfoService {
    static final int SALT_INTERACTIONS = 1000;
    private final UserInfoP2CConverter userInfoP2CConverter;
    private final UserInfoDao userInfoDao;

    @Autowired
    public UserInfoServiceIpl(UserInfoP2CConverter userInfoP2CConverter,
                              UserInfoDao userInfoDao) {
        this.userInfoP2CConverter = userInfoP2CConverter;
        this.userInfoDao = userInfoDao;
    }

    @Override
    public UserInfoCommon getUserInfoById(int id) {
        return Optional.ofNullable(userInfoDao.getUserInfoById(id))
            .map(userInfoP2CConverter::convert)
            .orElseThrow(() -> new ResourceNotFoundException("找不到该用户"));
    }

    @Override
    public UserInfoCommon getUserInfoByUsername(String username) {
        return Optional.ofNullable(userInfoDao.getUserInfoByUsername(username))
            .map(userInfoP2CConverter::convert)
            .orElseThrow(() -> new ResourceNotFoundException("找不到该用户"));
    }

    @Override
    public void login(String username, String password) {
        // 代表当前用户
        Subject subject = SecurityUtils.getSubject();
        // 创建 token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 登录
        subject.login(token);
    }

    @Override
    public UserInfoCommon register(String username, String password) {
        UserInfoPersistence userInfo = userInfoDao.getUserInfoByUsername(username);

        if (userInfo != null) {
            throw new InvalidParameterException("该用户名已存在");
        }

        String salt = UUID.randomUUID().toString();
        String encryptedPassword = new Sha256Hash(password, salt, SALT_INTERACTIONS).toBase64();

        UserInfoPersistence newUserInfo = new UserInfoPersistenceBuilder()
            .setUsername(username)
            .setPassword(encryptedPassword)
            .setSalt(salt)
            .setCreateTime(Instant.now())
            .createUserInfo();

        userInfoDao.createNewUser(newUserInfo);

        return userInfoP2CConverter.convert(newUserInfo);
    }
}
