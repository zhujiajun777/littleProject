package com.example.demo.controller;

import com.example.demo.service.UserRoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO: 用于系统管理的接口类
 *
 * @author zhujiajun
 * @date 2020/8/26 16:00
 */
@Controller
@Api("系统管理")
@RequestMapping(value = "sys")
@Slf4j
public class SysManageController {

    @Autowired
    private UserRoleService userRoleService;

}
