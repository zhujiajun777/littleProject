package com.example.demo.controller;

import com.example.demo.model.ResultEnum;
import com.example.demo.model.SysRole;
import com.example.demo.service.UserRoleService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @ApiOperation(value = "获取用户角色" , notes = "获取用户角色")
    public Result getRoleListByUserCode(@RequestBody Map<String,Object> requestMap){

        log.info("getRole input data : " + requestMap);

        try {
            String code = (String) requestMap.get("code");
            if (StringUtils.isEmpty(code)){
                return ResultUtils.error("参数不能为空!");
            }
            return ResultUtils.sucess(userRoleService.selectRoleByUserCode(code)) ;
        } catch (Exception e) {
            log.error("getRole error : " + e.getMessage(),e);
            return ResultUtils.error(ResultEnum.SYSTEM_ERROR);
        }

    }

    @PostMapping(value = "/setRole")
    @ApiOperation(value = "配置用户角色" , notes = "配置用户角色")
    public Result setUserRoleMapping(@RequestBody Map<String,Object> requestMap){

        log.info("setRole input data : " + requestMap);

        try {
            return userRoleService.insertRoleMapping(requestMap);
        } catch (Exception e) {
            log.error("setRole error : " + e.getMessage(),e);
            return ResultUtils.error(ResultEnum.SYSTEM_ERROR);
        }
    }

}
