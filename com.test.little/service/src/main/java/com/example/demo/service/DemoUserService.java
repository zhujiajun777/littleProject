package com.example.demo.service;

import com.example.demo.model.DemoUser;

import java.util.List;
import java.util.Map;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/7/10 15:41
 */
public interface DemoUserService {

    public DemoUser selectOneDemoUser(Integer key);

    public DemoUser queryUserByName(String userName);

    public List<DemoUser> selectUserList(Map<String,Object> param);

}
