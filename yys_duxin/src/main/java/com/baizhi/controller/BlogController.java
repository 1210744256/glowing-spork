package com.baizhi.controller;

import com.baizhi.dto.Result;
import com.baizhi.entity.Blog;
import com.baizhi.entity.User;
import com.baizhi.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/of/me")
    public Result selectByPageOfMe(Integer current, HttpServletRequest request){
        if(current==null)current=1;
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        return blogService.queryByLimitForMe(user.getId(),current,2);
    }
    @PostMapping
    public Result saveBlog(@RequestBody Blog blog, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        blog.setUserId(user.getId());
        blog.setComments(0);
        blog.setLiked(0);
        blogService.saveBlog(blog);
        return new Result().ok();
    }
    @PutMapping("like/{blogId}")
    public Result clickLiked(@PathVariable Integer blogId,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        blogService.clickLike(blogId, user.getId());
        return new Result().ok();
    }
    @GetMapping("hot")
    public Result queryByLimitOfHot(Integer current){
        if(current==null)current=1;
        return blogService.queryByLimitHot(current,3);
    }
    @GetMapping("/{blogId}")
    public Result queryById(@PathVariable Integer blogId){
        return blogService.queryById(blogId);
    }
    @GetMapping("of/user")
    public Result queryLimitOfUser(Integer current,Integer id){
        if(current==null)current=1;
        return blogService.queryByLimitForMe(id,current,3);
    }
//    查询某个blog的前五个点赞用户
    @GetMapping("/likes/{id}")
    public Result queryByLimitIdOfTop5(@PathVariable Integer id){
//        blogService.queryByLimitForMe(id)
        return blogService.queryByLimitHot5OfUser(1,id,5);
    }
//    我关注的好友的笔记
    @GetMapping("of/follow")
    public Result myFriendBlog(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        return blogService.queryMyFriendBlog(user.getId());
    }
//    上传blog图片

}
