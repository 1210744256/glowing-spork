package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;


public interface UserService {
    Map<String,Object> regist(User user);
    Map<String,Object> login(User user);
    List<User> queryAll();
    void deleteById(int id);
    void update(User user);
    Map<String,Object> selectLimit(int page,int size);
}
