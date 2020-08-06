package com.example.demo.service.impl;

import com.example.demo.mapper.DemoUserMapper;
import com.example.demo.model.DemoUser;
import com.example.demo.service.DemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/4 17:17
 */
@Service
public class DemoUserServiceImpl implements DemoUserService {

    @Autowired
    DemoUserMapper demoUserMapper;

    @Override
    public DemoUser selectOneDemoUser(Integer key) {
        return demoUserMapper.selectByPrimaryKey(key);
    }

    @Override
    public DemoUser queryUserByName(String userName) {
        return demoUserMapper.selectUserByName(userName);
    }
}
