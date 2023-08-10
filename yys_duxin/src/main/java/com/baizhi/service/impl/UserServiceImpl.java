package com.baizhi.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baizhi.dao.SignDao;
import com.baizhi.dao.UserDao;
import com.baizhi.dto.Result;
import com.baizhi.entity.Sign;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SignDao signDao;
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
                user1.setNickName(phone);
                user1.setCreateTime(new Date());
                user1.setUpdateTime(new Date());
                userDao.insert(user1);
                user=user1;
            }
            return new Result().ok(user);
        }
        User user = userDao.queryByPhone(phone);
        if(user==null||!user.getPassword().equals(password)) return new Result().error(null,"密码错误");
       return new Result().ok(user);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void insertSign(Sign sign) {
        signDao.insert(sign);
    }

    @Override
    public Integer SignCount() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        List<Sign> signs = signDao.queryAllOfDate();
        Calendar next=Calendar.getInstance();
//        先设置连续签到时间为0
        int day=0;
        for (Sign sign : signs) {
            Date date = sign.getDate();
            next.setTime(date);
            next.set(Calendar.HOUR_OF_DAY, 0);
            next.set(Calendar.MINUTE, 0);
            next.set(Calendar.SECOND, 0);
            next.set(Calendar.MILLISECOND, 0);
           if((now.getTime().getTime()-next.getTime().getTime())/(1000*60*60*24)==day){
               day++;
           }else {
               break;
           }
        }
        return day;
    }


}
