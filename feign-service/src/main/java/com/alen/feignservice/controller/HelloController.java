package com.alen.feignservice.controller;

import com.alen.feignservice.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alen
 * @create 2018-08-15 12:24
 **/
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;
    /**
     * 通过Controller调用远程服务
     * @param age
     * @return
     */
    @RequestMapping("/hello")
    public String home(@RequestParam Integer age) {
      age= helloService.hello(age);
        System.out.println(age);
        return "Result";
    }
}
