package com.example.demo.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/4 11:17
 */
public class UserRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        //用户名 + 密码

        String name = "admin";
        String password = "admin";

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        if (!name.equals(token.getUsername())){
            //自动抛出异常
            return null;
        }

        //shiro密码认证
        return new SimpleAuthenticationInfo("",password,"");
    }

}
