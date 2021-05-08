package com.mydomain.accounting.config;

import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserShiro extends AuthorizingRealm {
    private final UserInfoService userInfoService;

    public UserShiro(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        UserInfoCommon userInfo = userInfoService.getUserInfoByUsername(username);

        if (!password.equals(userInfo.getPassword())) {
            throw new IncorrectCredentialsException("密码错误");
        }

        return new SimpleAuthenticationInfo(username, password, this.getName());
    }
}
