package com.baizhi.service.impl;

import com.baizhi.dao.BlogDao;
import com.baizhi.dao.FollowDao;
import com.baizhi.dto.Result;
import com.baizhi.entity.Blog;
import com.baizhi.entity.Liked;
import com.baizhi.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private FollowDao followDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Result queryByLimitForMe(Integer userId, Integer page, Integer size) {
        List<Blog> blogs = blogDao.queryByLimitForMe(userId, (page - 1) * size, size);
        return new Result().ok(blogs);
    }

    @Override
    public void saveBlog(Blog blog) {
        blogDao.insert(blog);
    }

    @Override
    public void clickLike(int blogId, int userId) {
        Liked liked = new Liked();
        liked.setLiked(1);
        liked.setBlogId(blogId);
        liked.setUserId(userId);
        liked.setCreateTime(new Date());
        liked.setUpdateTime(new Date());
        log.debug("liked{}:"+liked);
        blogDao.insertLiked(liked);
        Integer integer = blogDao.selectByLiked(blogId);
        blogDao.updateByLiked(blogId,++integer);
    }

    @Override
    public Result queryByLimitHot(Integer page, Integer size) {
        return new Result().ok( blogDao.queryByLimitForHot((page-1)*size,size));
    }

    @Override
    public Result queryById(int id) {
        return new Result().ok(blogDao.selectById(id));
    }

    @Override
    public Result queryByLimitHot5OfUser(int page, int blogId, int size) {
        return new Result().ok(blogDao.queryByLimitHot5OfUser((page-1)*size,size,blogId));
    }

    @Override
    public Result queryMyFriendBlog(int userId) {
//现根据用户id查询出关注的朋友id
        List<Integer> friendId = followDao.queryAttention(userId);
//        根据朋友id查询出所有的blog
        return new Result().ok(blogDao.queryAllBlogOfDistanct(friendId));
    }

}
