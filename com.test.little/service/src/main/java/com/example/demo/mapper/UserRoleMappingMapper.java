package com.example.demo.mapper;

import com.example.demo.model.UserRoleMapping;

public interface UserRoleMappingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRoleMapping record);

    int insertSelective(UserRoleMapping record);

    UserRoleMapping selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRoleMapping record);

    int updateByPrimaryKey(UserRoleMapping record);
}