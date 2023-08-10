package com.baizhi.dao;

import com.baizhi.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogDao extends BaseDao<Blog,Integer>{
    List<Blog> queryByLimitForMe(@Param("userId") Integer userId,@Param("startIndex") Integer startIndex,@Param("size") Integer size);
}
