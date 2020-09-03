package com.example.demo.controller;


import com.example.demo.service.DemoUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/26 16:22
 */
@Controller
@Api("系统一般接口类")
@RequestMapping(value = "common")
@Slf4j
public class CommonController {

    @Autowired
    private DemoUserService demoUserService;


}
