package com.alen.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alen
 * @create 2018-08-18 12:20
 **/
@RestController
public class HelloController {
    @Value("${name}")
    private String name;
    @Value("${democonfigclient.message}")
    private String message;
    /**
     * 从配置中心读取的变量的值
     * @param
     * @return
     */
    @RequestMapping("/getname")
    public String getName() {
        System.out.println(name);
        System.out.println(message);
        return name;
    }
}
