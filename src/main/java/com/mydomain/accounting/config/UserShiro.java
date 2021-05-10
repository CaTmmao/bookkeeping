package com.mydomain.accounting.config;

import com.mydomain.accounting.model.common.UserInfoCommon;
import com.mydomain.accounting.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserShiro extends AuthorizingRealm {
    private final UserInfoService userInfoService;

    /**
     * @param matcher 注入 config/ShiroConfig 文件中定义的 matcher 方法
     */
    public UserShiro(UserInfoService userInfoService, HashedCredentialsMatcher matcher) {
        super(matcher);
        this.userInfoService = userInfoService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        UserInfoCommon userInfo = userInfoService.getUserInfoByUsername(username);

        String password = userInfo.getPassword();
        ByteSource salt = ByteSource.Util.bytes(userInfo.getSalt());

        return new SimpleAuthenticationInfo(username, password, salt, this.getName());
    }
}
