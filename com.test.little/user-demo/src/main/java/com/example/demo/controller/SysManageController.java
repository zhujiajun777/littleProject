package com.example.demo.controller;

import com.example.demo.model.SysRole;
import com.example.demo.service.UserRoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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


    @PostMapping(value = "/getRole")
    @ResponseBody
    public List<SysRole> getRoleListByUserCode(@RequestBody Map<String,Object> requestMap){

        log.info("getRole input data : " + requestMap);
        String code = (String) requestMap.get("code");
        if (StringUtils.isEmpty(code)){
            return null;
        }
        return userRoleService.selectRoleByUserCode(code);
    }

}
