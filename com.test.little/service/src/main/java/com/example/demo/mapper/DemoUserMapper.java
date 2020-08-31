package com.example.demo.mapper;

import com.example.demo.model.DemoUser;

import java.util.List;
import java.util.Map;

public interface DemoUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DemoUser record);

    int insertSelective(DemoUser record);

    DemoUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DemoUser record);

    int updateByPrimaryKey(DemoUser record);

    DemoUser selectUserByName(String userName);

    List<DemoUser> selectUserList(Map<String, Object> paramMap);
}