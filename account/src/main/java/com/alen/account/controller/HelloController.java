package com.alen.account.controller;

import com.alen.account.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alen
 * @create 2018-08-11 9:35
 **/
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    /**
     * 服务调用 消费者
     * @param age
     * @return
     */
    @RequestMapping("/getage")
    public String getConsumer(@RequestParam Integer age) {
        return helloService.getAge(age);
    }
}
