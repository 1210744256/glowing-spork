package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserDao {
    void insert(User user);
//    根据手机号来查询对应的user
    User queryByPhone(String phone);
    List<User> queryByIds(@Param("ids")List<Integer> ids);
}
