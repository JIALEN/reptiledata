package com.alen.user.service.impl;

import com.alen.user.dao.UserDao;
import com.alen.user.model.entity.User;
import com.alen.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author alen
 * @create 2018-08-09 15:29
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public void add(User user) {
        userDao.insert(user);
    }

    @Override
    public List<User> find(Map<String, Object> map) {
        return userDao.find(map);
    }
}
