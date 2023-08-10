package com.baizhi.service.impl;

import com.baizhi.dao.FollowDao;
import com.baizhi.dao.UserDao;
import com.baizhi.dao.UserInFoDao;
import com.baizhi.dto.Result;
import com.baizhi.entity.Follow;
import com.baizhi.service.FollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowDao followDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Result isAttention(int userId, int followId) {
        Follow follow = followDao.selectYesOrNot(userId, followId);
        Boolean flag=true;
        if(follow==null)flag=false;
        return new Result().ok(flag);
    }
    @Override
    public void insert(Follow follow) {
        followDao.insert(follow);
    }
    @Override
    public void deleteByIdAndFollowId(int userId, int followId) {
    followDao.deleteByIdAndFollowId(userId,followId);
    }

    @Override
    public Result queryCommon(int userId1, int userId2) {
        List<Integer> user1 = followDao.queryAttention(userId1);
        List<Integer> user2 = followDao.queryAttention(userId2);
        log.debug("user1:{}"+user1);
        log.debug("user2:{}"+user2);
        user1.retainAll(user2);
        if(user1.isEmpty()){
            return new Result().ok();
        }
        return new Result().ok(userDao.queryByIds(user1));
    }
}
