package com.baizhi.dao;

import com.baizhi.entity.User;

public interface UserDao {
    void insert(User user);
//    根据手机号来查询对应的user
    User queryByPhone(String phone);
}
