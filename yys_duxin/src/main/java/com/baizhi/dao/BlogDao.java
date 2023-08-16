package com.baizhi.dao;

import com.baizhi.entity.Blog;
import com.baizhi.entity.Liked;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogDao extends BaseDao<Blog, Integer> {
    List<Blog> queryByLimitForMe(@Param("userId") Integer userId, @Param("startIndex") Integer startIndex, @Param("size") Integer size);

    //    点赞表的添加
    void insertLiked(Liked liked);

    //    查询blog的点赞数
    Integer selectByLiked(int id);

    //    改变点赞数
    void updateByLiked(@Param("id") int id, @Param("liked") int liked);

    //    查询热点blog
    List<Blog> queryByLimitForHot(@Param("startIndex") Integer startIndex,
                                  @Param("size") Integer size);
//    查询某个blog的前五个点赞用户
    List<User> queryByLimitHot5OfUser(@Param("startIndex") Integer startIndex,
                                      @Param("size") Integer size,@Param("blogId")Integer blogId);
//根据用户id集合查询出不重复的笔记
    List<Blog> queryAllBlogOfDistanct(@Param("ids") List<Integer> userId);
}
