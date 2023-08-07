package com.baizhi.dao;

import com.baizhi.entity.User;

import java.util.List;

public interface UserDao {
    List<User> select(User user);
    void insert(User user);
}
