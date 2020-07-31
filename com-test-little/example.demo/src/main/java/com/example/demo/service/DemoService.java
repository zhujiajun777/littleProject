package com.example.demo.service;

import com.example.demo.mapper.DemoUserMapper;
import com.example.demo.model.DemoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/7/10 15:41
 */
@Service
public class DemoService {

    @Autowired
    private DemoUserMapper demoUserMapper;

    public DemoUser selectOneDemoUser(){
        return demoUserMapper.selectByPrimaryKey(1);
    }

}
