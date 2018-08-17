package com.alen.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//扫描注解包
@ComponentScan(basePackages ="com.alen.user")
//指定要扫描的Mapper类的包的路径
@MapperScan(basePackages ="com.alen.user.dao")

//通过注解@EnableEurekaClient 表明自己是一个eurekaclient.
@EnableEurekaClient
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
