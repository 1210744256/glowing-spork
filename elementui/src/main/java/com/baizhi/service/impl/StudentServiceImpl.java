package com.baizhi.service.impl;

import com.baizhi.dao.StudentDao;
import com.baizhi.entity.Group;
import com.baizhi.entity.Student;
import com.baizhi.entity.Tag;
import com.baizhi.service.GroupService;
import com.baizhi.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private GroupService groupService;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectLimit(Student student, int page, int size) {
        logger.debug("student:" + student);
        Map<String, Object> map = new HashMap<>();
        int count = studentDao.queryCount(student);
        int totalPage = 0;
        if (count % size == 0) {
            totalPage = count / size;
        } else {
            totalPage = (count / size) + 1;
        }
        if (totalPage == 0) totalPage = 1;
        map.put("totalPage", totalPage);
        List<Student> students = studentDao.selectLimit(student, (page - 1) * size, size);
//        组标签
//        先查出所有组
        List<Group> groups = groupService.queryAll();
//        根据所有组查出每个学生集合
        for (Group group : groups) {


        }
        for (Student student1 : students) {

            logger.debug("student1" + student1);
        }
        map.put("students", students);
        return map;
    }


    @Override
    public void deleteById(int id) {
        studentDao.deleteById(id);
        studentDao.deleteRelation(id);
    }

    @Override
    public void update(Student student) {
        Group group = groupService.queryById(student.getGroup().getId());
        student.setClazz(group.getClazz());
        studentDao.update(student);
        studentDao.deleteRelation(student.getId());
        List<Integer> tids = new ArrayList<>();
        List<Tag> tags = student.getTags();
        for (Tag tag : tags) {
            tids.add(tag.getId());
        }
        List<Integer> sids = new ArrayList<>();
        sids.add(student.getId());
        studentDao.insertRelation2(sids, tids);
//        for (Integer tagid : tid) {
//            studentDao.insertRelation(student.getId(), tagid);
//        }
//        todo
    }

    @Override
    public void insert(Student student) {
        Group group = groupService.queryById(student.getGroup().getId());
        student.setClazz(group.getClazz());
        studentDao.insert(student);
        List<Integer> tid = new ArrayList<>();
        List<Tag> tags = student.getTags();
        System.out.println(student.getId());
        for (Tag tag : tags) {
            tid.add(tag.getId());
        }
        for (Integer tagid : tid) {
            studentDao.insertRelation(student.getId(), tagid);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int selectGroupStudentCount(int id) {
        return studentDao.selectGroupStudentCount(id);
    }

    @Override
    public List<Student> select(Student student) {
        return studentDao.select(student);
    }
}
