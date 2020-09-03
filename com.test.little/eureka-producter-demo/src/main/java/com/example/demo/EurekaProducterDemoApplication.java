package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * TODO: 服务提供者的示例启动类
 * @author zhujiajun
 * @date 2020/9/1 15:22
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaProducterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProducterDemoApplication.class,args);
    }

}
