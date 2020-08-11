package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/11 10:30
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                //接口文档页面展示的信息
                .apiInfo(apiInfo())
                //选择哪些路径和API生成文档
                .select()
                //需要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                //对所有的API进行监控
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                //标题
                .title("这里是swagger2的接口文档!")
                //描述
                .description("一些用来测试的接口")
                //作者
                .contact(new Contact("zhujiajun777","https://github.com/zhujiajun777/littleProject","1038642703@qq.com"))
                //版本号
                .version("V 0.1")
                .build();
    }

}
