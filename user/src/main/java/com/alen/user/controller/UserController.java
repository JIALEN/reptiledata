package com.alen.user.controller;

import com.alen.user.model.entity.User;
import com.alen.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author alen
 * @create 2018-08-09 15:34
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return"index";
    }

    @RequestMapping("/find")
    public List<User> find(@RequestParam Map<String,Object> map) {
        return userService.find(map);
    }
}
