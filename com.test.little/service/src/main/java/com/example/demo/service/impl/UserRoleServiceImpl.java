package com.example.demo.service.impl;

import com.example.demo.mapper.SysRoleMapper;
import com.example.demo.mapper.UserRoleMappingMapper;
import com.example.demo.model.SysRole;
import com.example.demo.service.UserRoleService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/26 15:56
 */
@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private UserRoleMappingMapper userRoleMappingMapper;

    @Override
    public int deleteByUserCode(String userCode) {
        return userRoleMappingMapper.deleteByUserCode(userCode);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result insertRoleMapping(Map<String, Object> paramMap) {
        String userCode = (String) paramMap.get("userCode");
        int delNum = userRoleMappingMapper.deleteByUserCode(userCode);
        log.info("用户 :" + userCode + "的角色映射关系已删除 ! " + delNum);
        paramMap.put("createTime", new Date());
        userRoleMappingMapper.insertRoleMapping(paramMap);
        return ResultUtils.sucess("角色配置成功!");
    }


}
