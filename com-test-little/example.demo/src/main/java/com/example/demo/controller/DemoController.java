package com.example.demo.controller;

import com.example.demo.model.DemoUser;
import com.example.demo.service.DemoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/7/10 15:46
 */
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/com/example/demo")
    @ResponseBody
    public DemoUser demo(){
        return demoService.selectOneDemoUser();
    }

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){

        model.addAttribute("msg","HelloShiro !");

        return "index";
    }

    @RequestMapping({"/user/add"})
    public String toAdd(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String toUpdate(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login( String username , String password , Model model){

        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户的登陆信息
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        try {
            //执行登陆方法
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误!");
            return "login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误!");
            return "login";
        }


    }

}
