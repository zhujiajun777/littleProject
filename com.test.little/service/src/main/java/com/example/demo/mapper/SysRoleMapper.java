package com.example.demo.mapper;

import com.example.demo.model.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectRoleByUserCode(String code);
}