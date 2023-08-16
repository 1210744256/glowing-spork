package com.baizhi.service;

import com.baizhi.dto.Result;
import com.baizhi.entity.Blog;

public interface BlogService {
    Result queryByLimitForMe(Integer userId, Integer page, Integer size);

    void saveBlog(Blog blog);

    //    点赞blog
    void clickLike(int blogId, int userId);

    //    查询热点
    Result queryByLimitHot(Integer page, Integer size);
    Result queryById(int id);
//    查询某个blog的前五个点赞用户
    Result queryByLimitHot5OfUser(int page,int blogId,int size);
    Result queryMyFriendBlog(int userId);
}
