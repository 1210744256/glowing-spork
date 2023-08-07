package com.baizhi.dao;

import com.baizhi.entity.Group;

import java.util.List;

public interface GroupDao {
    List<Group> queryAll();
    void delete(int id);
    void insert(Group group);
    Group queryById(int id);
}
