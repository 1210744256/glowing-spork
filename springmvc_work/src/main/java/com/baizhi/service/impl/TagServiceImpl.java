package com.baizhi.service.impl;

import com.baizhi.dao.TagDao;
import com.baizhi.dto.TagResponse;
import com.baizhi.entity.Tag;
import com.baizhi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Tag> queryAll() {
        return tagDao.queryAll();
    }

    @Override
    public void deleteById(int id) {
        tagDao.deleteById(id);
    }

    @Override
    public void insert(Tag tag) {
        tagDao.insert(tag);
    }

    @Override
    public List<Tag> queryTypeAll(String type) {
        return tagDao.queryTypeAll(type);
    }

    @Override
    public List<TagResponse> queryEchas() {
        return tagDao.queryEchas();
    }
}
