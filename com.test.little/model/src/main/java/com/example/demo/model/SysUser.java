package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {
    private Long id;

    private String roleName;

    private String status;

    private String remark;

    private Date createTime;

    private String createId;

    private Date updateTime;

    private String updateId;

}