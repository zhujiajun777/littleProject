package com.example.demo.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/4 11:17
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(defaultWebSecurityManager);

        /**
         *  anon    :   无需认证就可以访问
         *  authc   :   必须认证了才能访问
         *  user    :   必须拥有了 记住我 功能才能用
         *  perms   :   拥有对某个资源的权限才能访问
         *  role    :   拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/user/add", "perms[add]");
        filterMap.put("/user/update", "perms[update]");

        filterMap.put("/user/*", "authc");

        //添加具体的权限控制
        factoryBean.setFilterChainDefinitionMap(filterMap);

        //设置无权登陆页面
        factoryBean.setUnauthorizedUrl("/noAuth");

        //设置的登陆链接
        factoryBean.setLoginUrl("/toLogin");

        return factoryBean;
    }

}
