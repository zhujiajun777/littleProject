package com.example.demo.mapper;

import com.example.demo.model.UserRoleMapping;

import java.util.Map;

public interface UserRoleMappingMapper {
    int deleteByUserCode(String userCode);

    int insert(UserRoleMapping record);

    int insertSelective(UserRoleMapping record);

    UserRoleMapping selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRoleMapping record);

    int updateByPrimaryKey(UserRoleMapping record);

    int insertRoleMapping(Map<String,Object> paramMap);

}