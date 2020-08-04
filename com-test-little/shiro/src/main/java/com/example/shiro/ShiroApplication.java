package com.example.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/4 14:15
 */
@SpringBootApplication
@MapperScan(value = {"com.example.demo.mapper"})
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }

}
