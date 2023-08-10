package com.baizhi.dao;

import com.baizhi.entity.Follow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowDao extends BaseDao<Follow,Integer> {
    Follow selectYesOrNot(@Param("userId")Integer userId,@Param("followId")Integer b);
    void deleteByIdAndFollowId(@Param("userId")int userId,@Param("followId")int followId);
    List<Integer> queryAttention(int userId);
}
