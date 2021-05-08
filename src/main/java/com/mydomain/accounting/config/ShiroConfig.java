package com.mydomain.accounting.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Bean
    public SecurityManager securityManager(Realm realm) {
        return new DefaultWebSecurityManager(realm);
    }

    /**
     * Shiro Filter 实现权限相关的拦截
     * <p>
     * 拦截器类型
     * anon: 无需登录就可访问
     * authc: 需要登录才可访问
     * user: 点击 记住我 可自动访问
     * role: 是相关角色才可访问
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        LinkedHashMap<String, String> shiroFilterDefinitionMap = new LinkedHashMap<>();

        // 设置有先后顺序，先 put 进去的先应用
        shiroFilterDefinitionMap.put("/v1.0/users", "anon"); // 用户相关接口，无需登录就可访问
        shiroFilterDefinitionMap.put("/v1.0/session", "anon");
        shiroFilterDefinitionMap.put("/**", "authc"); // 其他接口，需要登录才能访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterDefinitionMap);

        return shiroFilterFactoryBean;
    }
}
