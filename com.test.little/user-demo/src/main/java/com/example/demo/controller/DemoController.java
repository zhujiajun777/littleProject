package com.example.demo.controller;

import com.example.demo.config.ApiJsonObject;
import com.example.demo.config.ApiJsonProperty;
import com.example.demo.model.DemoUser;
import com.example.demo.service.DemoUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.Workbook;
import jxl.format.*;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.Address;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/7/10 15:46
 */
@Controller
@Api("测试接口类")
@Slf4j
public class DemoController {

    @Autowired
    private DemoUserService demoService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping(value = "/com/example/demo")
    @ResponseBody
    @ApiOperation(value = "user对象的测试类", notes = "入参为map,可以为空")
    public DemoUser demo(@ApiJsonObject(name = "RequestModel", value = {
            @ApiJsonProperty(key = "id", example = "1", description = "用户code", type = "int", required = true),
            @ApiJsonProperty(key = "userName", example = "admin", description = "用户名", type = "string", required = false)
    }) @RequestBody Map<String, Object> map) {
        Integer id = (Integer) map.get("id");
        String userName = (String) map.get("userName");
        redisTemplate.boundValueOps("user").set(demoService.selectOneDemoUser(id).toString());
        log.info("==========>user信息存入了redis缓存!");

        log.info("----------------------------->" + demoService.selectOneDemoUser(id));

        Object user = redisTemplate.boundValueOps("user").get();
        log.info("==========>从redis缓存的中获取user信息:");
        log.info(user.toString());

        if (!"".equals(id.toString())) {
            return demoService.selectOneDemoUser(id);
        }
        if (!StringUtils.isEmpty(userName)) {
            return demoService.queryUserByName(userName);
        }
        return new DemoUser();
    }

    @GetMapping(value = {"/", "/index"})
    @ApiOperation(value = "首页", notes = "进入首页")
    public String toIndex(Model model) {

        model.addAttribute("msg", "HelloShiro !");

        return "index";
    }

    @GetMapping(value = "/user/add")
    @ApiOperation(value = "add页面", notes = "add页面")
    public String toAdd() {
        return "user/add";
    }

    @GetMapping(value = "/user/update")
    @ApiOperation(value = "update页面", notes = "update页面")
    public String toUpdate() {
        return "user/update";
    }

    @GetMapping(value = "/toLogin")
    @ApiOperation(value = "进入登陆页面", notes = "进入登陆页面")
    public String toLogin() {
        return "login";
    }

    @GetMapping(value = "/login")
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户的登陆信息
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            //执行登陆方法
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误!");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误!");
            return "login";
        }
    }

    @GetMapping(value = "/logout")
    @ApiOperation(value = "注销登陆", notes = "注销登陆")
    public String logout() {

        log.info("user logout ! ");

        try {
            Subject subject = SecurityUtils.getSubject();
            //如果用户登陆了
            if (subject.isAuthenticated()) {

                //执行登陆方法
                subject.logout();

                log.info(subject.getPrincipals() + " 用户登出成功!");
                return "login";
            }
        } catch (Exception e) {
            log.error("user logout error : " + e.getMessage(), e);
            return "login";
        }
        return "login";
    }

    @GetMapping(value = "/noAuth")
    @ResponseBody
    @ApiOperation(value = "无权限", notes = "无权限")
    public String unAuthorized() {
        return "未授权午发访问!";
    }

    @GetMapping("/hello")
    public String helloUser(){

        log.info(String.valueOf(SecurityUtils.getSubject().getPrincipals()));

        log.info(String.valueOf(SecurityUtils.getSubject().getPrincipal()));

        return String.valueOf(SecurityUtils.getSubject());
    }

}
