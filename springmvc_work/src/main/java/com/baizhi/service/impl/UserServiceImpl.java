package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Map<String, Object> regist(User user) {
        Map<String,Object> map=new HashMap<>();
        if(user.getUsername().equals("")&&user.getPassword().equals("")){
            map.put("message","请输入信息");
            return map;
        }
        if(user.getUsername().equals("")){
            map.put("message","请输入用户名");
            return map;
        }
        User user1=new User();
        user1.setUsername(user.getUsername());
        List<User> select = userDao.select(user1);
        if(!select.isEmpty()){
            map.put("message","用户名已存在,请重新输入");
            return map;
        }
        user.setRole("general");
        user.setCreateDate(new Date());
        userDao.insert(user);
        map.put("message","注册成功");
        map.put("user",user);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> login(User user) {
        Map<String,Object> map=new HashMap<>();
        if(user==null){
            map.put("message","请输入信息");
            return map;
        }
        User user1=new User();
        user1.setUsername(user.getUsername());
        List<User> select = userDao.select(user1);
        if(select.isEmpty()){
            map.put("message","用户名不存在,请重新输入");
            return map;
        }
        User jian = select.get(0);
        if(!jian.getPassword().equals(user.getPassword())){
            map.put("message","密码错误,请重新输入");
            return map;
        }
        map.put("message","success");
        map.put("user",jian);
//        map.put("login","success");
        return map;
    }
}
