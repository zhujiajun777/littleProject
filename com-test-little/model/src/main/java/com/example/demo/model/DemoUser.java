package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DemoUser implements Serializable {
    private Integer id;

    private String code;

    private String userName;

    private String password;

    private String perms;

    private Date updateTime;

}