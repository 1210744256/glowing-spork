package com.baizhi.service;

import com.baizhi.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> queryAll();
    void delete(int id);
    void insert(Group group);
    Group queryById(int id);
}
