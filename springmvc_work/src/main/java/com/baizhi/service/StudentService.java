package com.baizhi.service;

import com.baizhi.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Map<String, Object> selectLimit(Student student, int page, int size);
    void deleteById(int id);
    void update(Student student);
    void insert(Student student);
    int selectGroupStudentCount(int id);
    List<Student> select(Student student);
}
