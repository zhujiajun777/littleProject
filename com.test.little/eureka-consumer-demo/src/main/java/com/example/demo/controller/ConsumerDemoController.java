package com.example.demo.controller;

import com.example.demo.service.ConsumerDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: 消费者示例接口类
 * @author zhujiajun
 * @date 2020/9/1 15:53
 */
@RestController
public class ConsumerDemoController {

    @Autowired
    private ConsumerDemoService consumerDemoService;

    @RequestMapping(value = "/hi/{name}")
    public String index(@PathVariable("name") String name){
        return consumerDemoService.demo(name);
    }

}
