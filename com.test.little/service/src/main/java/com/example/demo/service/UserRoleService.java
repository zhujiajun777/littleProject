package com.example.demo.service;

import com.example.demo.model.SysRole;
import com.example.demo.utils.Result;

import java.util.List;
import java.util.Map;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/26 15:55
 */
public interface UserRoleService {

    /**
     * 根据userCode删除用的角色映射关系
     *
     * @param userCode
     * @return
     */
    int deleteByUserCode(String userCode);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectRoleByUserCode(String code);

    /**
     * 新增用户的映射关系
     *
     * @param paramMap
     * @return
     */
    Result insertRoleMapping(Map<String, Object> paramMap);

}
