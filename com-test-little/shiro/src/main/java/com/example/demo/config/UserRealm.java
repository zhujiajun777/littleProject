package com.example.demo.config;

import com.example.demo.model.DemoUser;
import com.example.demo.service.DemoUserService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/4 11:17
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private DemoUserService demoUserService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();

//        Object userObject = subject.getPrincipal();
//
//        DemoUser user = new DemoUser();
//
//        try {
//            PropertyUtils.copyProperties(user,userObject);
//        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//            e.printStackTrace();
//        }

        DemoUser user = (DemoUser) subject.getPrincipal();

        info.addStringPermission(user.getPerms());

        System.out.println(user.getUserName() + "执行了授权认证!");

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {




        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        DemoUser user = demoUserService.queryUserByName(token.getUsername());
        System.out.println( token.getUsername() + "执行了登陆认证!");

        if (user==null){
            //自动抛出异常
            return null;
        }

        //shiro密码认证
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }

}
