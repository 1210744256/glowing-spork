package com.baizhi.controller;

import com.baizhi.dto.Result;
import com.baizhi.service.UserInFoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/user/info")
public class UserInFoController {
    @Autowired
    private UserInFoService userInFoService;
    @GetMapping("/{userId}")
    public Result selectById(@PathVariable String userId){
        log.debug("userId:"+userId);
        return userInFoService.selectById(Integer.parseInt(userId));
    }
}
