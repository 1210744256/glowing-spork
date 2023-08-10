package com.baizhi.service.impl;

import com.baizhi.dao.BlogDao;
import com.baizhi.dto.Result;
import com.baizhi.entity.Blog;
import com.baizhi.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Result queryByLimitForMe(Integer userId, Integer page, Integer size) {
        List<Blog> blogs = blogDao.queryByLimitForMe(userId, (page - 1) * size, size);
        return new Result().ok(blogs);
    }

}
