package com.alen.user.service;

import com.alen.user.model.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author alen
 * @create 2018-08-09 15:29
 **/
public interface UserService {

    void add(User user);

    List<User> find(Map<String, Object> map);
}
