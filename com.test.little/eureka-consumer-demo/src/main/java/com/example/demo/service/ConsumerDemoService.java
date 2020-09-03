package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spring-cloud-eureka-producer",path = "/demo")
public interface ConsumerDemoService {

    @GetMapping(value = "/hello")
    String demo(@RequestParam("name") String name);

}
