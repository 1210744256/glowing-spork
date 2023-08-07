package com.baizhi.service.impl;

import com.baizhi.dao.ClazzDao;
import com.baizhi.dto.ClazzResponse;
import com.baizhi.entity.Clazz;
import com.baizhi.service.ClazzService;
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
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    ClazzDao clazzDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Clazz> queryAll() {
        return clazzDao.queryAll();
    }

    @Override
    public void deleteById(int id) {
        clazzDao.deleteById(id);
        clazzDao.deleteRelation(id);
    }

    @Override
    public void insert(Clazz clazz) {
        clazzDao.insert(clazz);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Clazz queryById(int id) {
        return clazzDao.queryById(id);
    }

    @Override
    public Map<String, Object> clazzExcel() {
        List<ClazzResponse> clazzResponses = clazzDao.queryAllCount();
        Map<String,Object> map=new HashMap<>();
        List<String> clazzNames=new ArrayList<>();
        List<Integer> clazzStudentCounts=new ArrayList<>();
        for (ClazzResponse clazzRespons : clazzResponses) {
            clazzNames.add(clazzRespons.getName());
            clazzStudentCounts.add(clazzRespons.getClazzStudentCount());
        }
        map.put("clazzNames",clazzNames);
        map.put("clazzStudentCounts",clazzStudentCounts);
        return map;
    }
}
