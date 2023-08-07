package com.baizhi.dao;

import com.baizhi.dto.ClazzResponse;
import com.baizhi.entity.Clazz;

import java.util.List;

public interface ClazzDao {
    List<Clazz> queryAll();
    void deleteById(int id);
    void insert(Clazz clazz);
    Clazz queryById(int id);
    void deleteRelation(int id);
//    各班级学生数量以及班名
    List<ClazzResponse> queryAllCount();
}
