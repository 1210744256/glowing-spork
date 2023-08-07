package com.baizhi.service;

import com.baizhi.entity.Clazz;

import java.util.List;
import java.util.Map;

public interface ClazzService {
    List<Clazz> queryAll();
    void deleteById(int id);
    void insert(Clazz clazz);
    Clazz queryById(int id);
    Map<String,Object> clazzExcel();
}
