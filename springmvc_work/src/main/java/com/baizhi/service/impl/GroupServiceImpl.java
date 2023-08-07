package com.baizhi.service.impl;

import com.baizhi.dao.GroupDao;
import com.baizhi.dao.StudentDao;
import com.baizhi.dao.TagDao;
import com.baizhi.dto.GroupResponse;
import com.baizhi.entity.Group;
import com.baizhi.entity.Student;
import com.baizhi.entity.Tag;
import com.baizhi.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TagDao tagDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Group> queryAll() {
        List<Group> groups = groupDao.queryAll();
        List<Group> groupResponses = new ArrayList<>();
        for (Group group : groups) {
            Student student1 = new Student();
            student1.setGroup(group);
            List<Student> studentList = studentDao.select(student1);
//            遍历学生集合
            List<Integer> tagIds = new ArrayList<>();
            for (Student student2 : studentList) {
//                将每个学生的标签id信息传入到集合中
                List<Tag> tags = student2.getTags();
                for (Tag tag : tags) {
                    tagIds.add(tag.getId());
                }
            }
//            将id升序排列
            Collections.sort(tagIds);
            System.out.println("tagids:" + tagIds);
//            查出小组人数
            int groupStudentCount = studentDao.selectGroupStudentCount(group.getId());
            System.out.println("小组人数为："+groupStudentCount);
            List<Integer> newTid = new ArrayList<>();
//            方案1:如果有重复的标签就定义为小组标签
//            如果有重复元素保留没有则删除
//            for (int i = 0; i < tagIds.size(); ) {
//                int k = i + 1;
//                while (k < tagIds.size() - 1&&tagIds.get(i) == tagIds.get(k)) {
//                    k++;
//                }
//                if (k != i + 1) {
//                    newTid.add(tagIds.get(i));
//                }
//                    i = k;
//
//            }
//
//            方案2:查询集合中每个元素个数如果等于小组人数则定义为小组标签否则不是
            int i = 0;
            while (true) {
                if (i >= tagIds.size()) break;
                int k = i + 1;
                while (k < (tagIds.size() - 1) && tagIds.get(i) == tagIds.get(k)) {
                    k++;
                }
                if (k - i == groupStudentCount) {
                    newTid.add(tagIds.get(i));
                }
                i = k;
            }

//
//            map.put()
//            group.setStudentCount(count);
//
//            BeanUtils.copyProperties();

            GroupResponse groupResponse = new GroupResponse();
            groupResponse.setId(group.getId());
            groupResponse.setName(group.getName());
            groupResponse.setCreateDate(group.getCreateDate());
            groupResponse.setClazz(group.getClazz());
            groupResponse.setStudentCount(groupStudentCount);
//            将小组标签加入
            //            现在的newtagIds集合里存的就是每个组的组标签id
            System.out.println("newTid:" + newTid);
            if (!newTid.isEmpty()) {
                List<Tag> tags = tagDao.queryByIds(newTid);
                groupResponse.setTags(tags);
            }
            groupResponses.add(groupResponse);

        }
        return groupResponses;
    }

    @Override
    public void delete(int id) {
        groupDao.delete(id);
    }

    @Override
    public void insert(Group group) {
        groupDao.insert(group);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Group queryById(int id) {
        return groupDao.queryById(id);
    }
}
