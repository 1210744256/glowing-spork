package com.baizhi.service;

import com.baizhi.dto.Result;
import com.baizhi.entity.Follow;

import java.util.List;

public interface FollowService {
    Result isAttention(int userId,int followId);
    void insert(Follow follow);
    void deleteByIdAndFollowId(int userId,int followId);
    Result queryCommon(int userId1,int userId2);

}
