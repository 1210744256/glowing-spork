package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> select(User user);
    void insert(User user);
//    修改
    void update(User user);
//    删除
    void deleteById(int id);
    List<User> selectLimit(@Param("startIndex") int startIndex,@Param("size") int size);
//    查询总条数
    int queryCount();
}
