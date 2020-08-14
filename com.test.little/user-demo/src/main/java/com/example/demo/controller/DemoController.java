package com.example.demo.controller;

import com.example.demo.config.ApiJsonObject;
import com.example.demo.config.ApiJsonProperty;
import com.example.demo.model.DemoUser;
import com.example.demo.service.DemoUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

    @RequestMapping(value = "/com/example/demo" , method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "user对象的测试类" , notes = "入参为map,可以为空")
    public DemoUser demo(@ApiJsonObject(name = "RequestModel", value = {
            @ApiJsonProperty(key = "id" , example = "1" , description = "用户code" , type = "int" , required = true),
            @ApiJsonProperty(key = "userName" ,example = "admin" , description = "用户名" , type = "string" , required = false)
    })
            @RequestBody Map<String,Object> map){
        Integer id = (Integer) map.get("id");
        String userName = (String) map.get("userName");
        redisTemplate.boundValueOps("user").set(demoService.selectOneDemoUser(id).toString());
        log.info("==========>user信息存入了redis缓存!");

        log.info("----------------------------->" + demoService.selectOneDemoUser(id));

        Object user = redisTemplate.boundValueOps("user").get();
        log.info("==========>从redis缓存的中获取user信息:");
        log.info(user.toString());

        if (!"".equals(id.toString())){
            return demoService.selectOneDemoUser(id);
        }
        if (!StringUtils.isEmpty(userName)){
            return demoService.queryUserByName(userName);
        }
        return new DemoUser();
    }

    @RequestMapping(value = {"/","/index"} , method = RequestMethod.GET)
    @ApiOperation(value = "首页" , notes = "进入首页")
    public String toIndex(Model model){

        model.addAttribute("msg","HelloShiro !");

        return "index";
    }

    @RequestMapping(value = "/user/add" , method = RequestMethod.GET)
    @ApiOperation(value = "add页面" , notes = "add页面")
    public String toAdd(){
        return "user/add";
    }

    @RequestMapping(value = "/user/update" , method = RequestMethod.GET)
    @ApiOperation(value = "update页面" , notes = "update页面")
    public String toUpdate(){
        return "user/update";
    }

    @RequestMapping(value = "/toLogin" , method = RequestMethod.GET)
    @ApiOperation(value = "进入登陆页面" , notes = "进入登陆页面")
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    @ApiOperation(value = "用户登陆" , notes = "用户登陆")
    public String login(@RequestParam String username ,
                        @RequestParam String password ,
                        Model model){

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

    @RequestMapping(value = "/noAuth" , method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "无权限" , notes = "无权限")
    public String unAuthorized(){
        return "未授权午发访问!";
    }

}
