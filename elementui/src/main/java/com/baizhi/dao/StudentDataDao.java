package com.baizhi.dao;

import com.baizhi.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDataDao {
//    批量添加
    void insert(@Param("students") List<Student> students);
}
