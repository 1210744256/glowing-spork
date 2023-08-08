package com.baizhi.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baizhi.dao.UserDao;
import com.baizhi.dto.Result;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Result login(String phone, String password,Boolean login) {
        if(login){
            User user = userDao.queryByPhone(phone);
            if(user==null) {
                User user1=new User();
                user1.setPhone(phone);
                user1.setPassword(RandomUtil.randomNumbers(8));
//                user1.setIcon();
                user1.setCreateTime(new Date());
                user1.setUpdateTime(new Date());
                userDao.insert(user1);
                user=user1;
            }
            return new Result().ok(user);
        }
        User user = userDao.queryByPhone(phone);
        if(user==null||!user.getPassword().equals(password)) return new Result().error();
       return new Result().ok(user);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }
}
