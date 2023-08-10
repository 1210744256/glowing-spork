package com.baizhi.controller;

import com.baizhi.dto.Result;
import com.baizhi.entity.User;
import com.baizhi.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
