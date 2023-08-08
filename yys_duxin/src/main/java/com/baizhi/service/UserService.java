package com.baizhi.service;

import com.baizhi.dto.Result;
import com.baizhi.entity.User;

public interface UserService {
    Result login(String phone,String password,Boolean login);
    void insert(User user);
}
