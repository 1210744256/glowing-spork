package com.baizhi.listener;

import com.baizhi.dao.StudentDao;

import javax.annotation.Resource;

public class Sdus {
    @Resource
    private StudentDao studentDao;
    public void jsk(){
        System.out.println(studentDao);
    }
}
