package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserRoleMapping implements Serializable {
    private Long id;

    private String userCode;

    private String roleCode;

    private String status;

    private String createId;

    private Date createTime;

    private String delId;

    private Date delTime;

}