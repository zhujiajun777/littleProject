package com.example.demo.mapper;

import com.example.demo.model.DemoUser;

public interface DemoUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DemoUser record);

    int insertSelective(DemoUser record);

    DemoUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DemoUser record);

    int updateByPrimaryKey(DemoUser record);
}