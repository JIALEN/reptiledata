package com.alen.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alen
 * @create 2018-08-11 9:28
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String home(@RequestParam Integer age) {
        age=age+10;
        return age.toString();
    }

}
