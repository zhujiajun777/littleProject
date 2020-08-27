package com.example.demo.service.impl;

import com.example.demo.mapper.SysRoleMapper;
import com.example.demo.mapper.UserRoleMappingMapper;
import com.example.demo.model.SysRole;
import com.example.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/26 15:56
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private UserRoleMappingMapper userRoleMappingMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(SysRole record) {
        return 0;
    }

    @Override
    public int insertSelective(SysRole record) {
        return 0;
    }

    @Override
    public SysRole selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SysRole record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SysRole record) {
        return 0;
    }

    @Override
    public List<SysRole> selectRoleByUserCode(String code) {
        return sysRoleMapper.selectRoleByUserCode(code);
    }
}
