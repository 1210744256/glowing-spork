package com.baizhi.dao;

import com.baizhi.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    List<Student> selectLimit(@Param("student") Student student,@Param("startIndex") int startIndex,@Param("size") int size);
    void deleteById(int id);
    void update(Student student);
    void insert(Student student);
    int queryCount(Student student);
//    修改关系表
    void updateRelation(@Param("sid") int sid,@Param("tid") int tid);
//    删除关系表
    void deleteRelation(int sid);
//    添加关系表
    void insertRelation(@Param("sid") int sid,@Param("tid") int tid);
//    根据小组查询学生人数
    int selectGroupStudentCount(int id);
//    条件查询
    List<Student> select(Student student);
//    批量删除
//    批量添加
    void insertRelation2(@Param("sids")List<Integer> sids,@Param("tids") List<Integer>tids );
//    查出一个组的全部id

}
