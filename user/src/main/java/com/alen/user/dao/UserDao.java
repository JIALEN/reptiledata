package com.alen.user.dao;

import com.alen.user.model.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    Boolean deleteByPrimaryKey(Integer id);

    Boolean insert(User record);

    Boolean insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    Boolean updateByPrimaryKeySelective(User record);

    Boolean updateByPrimaryKey(User record);

    List<User> find(Map<String, Object> map);
}
