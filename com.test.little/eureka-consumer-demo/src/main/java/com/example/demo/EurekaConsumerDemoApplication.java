package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/9/1 15:38
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class EurekaConsumerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerDemoApplication.class,args);
    }

}
