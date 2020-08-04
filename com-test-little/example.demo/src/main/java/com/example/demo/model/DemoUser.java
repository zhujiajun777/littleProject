package com.example.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class DemoUser {
    private Integer id;

    private String code;

    private String userName;

    private String password;

    private String status;

    private Date updateTime;

}