package com.baizhi.controller;

import com.baizhi.dto.Result;
import com.baizhi.entity.Follow;
import com.baizhi.entity.User;
import com.baizhi.service.FollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    private FollowService followService;

    @GetMapping("/or/not/{userId}")
    public Result isAttention(@PathVariable Integer userId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return followService.isAttention(user.getId(), userId);
    }

    @PutMapping("/{id}/{isFollow}")
    public Result attentionOrNo(@PathVariable Integer id, @PathVariable Boolean isFollow, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (isFollow) {
            followService.deleteByIdAndFollowId(user.getId(), id);
        } else {
            Follow follow = new Follow();
            follow.setFollowUserId(id);
            follow.setUserId(user.getId());
            follow.setCreateTime(new Date());
            followService.insert(follow);
        }
        return new Result().ok();
    }

    @GetMapping("/common/{id}")
    public Result commonAttention(@PathVariable Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return followService.queryCommon(user.getId(), id);
    }
}
