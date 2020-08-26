package com.example.demo.service.impl;

import com.example.demo.mapper.SysUserMapper;
import com.example.demo.mapper.UserRoleMappingMapper;
import com.example.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/26 15:56
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserRoleMappingMapper userRoleMappingMapper;

}
