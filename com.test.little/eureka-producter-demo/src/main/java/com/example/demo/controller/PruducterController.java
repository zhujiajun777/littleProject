package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: 测试生产者接口类
 *
 * @author zhujiajun
 * @date 2020/9/1 15:24
 */
@RequestMapping(value = "demo")
@RestController
public class PruducterController {

    @Value("${spring.application.name}")
    private String SERVER_NAME;

    @GetMapping("/hello")
    public String demo(String name){
        return "Hello " + name + " , this is " + SERVER_NAME + " 服务 ! ";
    }

    @RequestMapping("/index")
    public String index(){
        return "Hello World!";
    }


}
