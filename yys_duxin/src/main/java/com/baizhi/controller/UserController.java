package com.baizhi.controller;

import cn.hutool.core.util.RandomUtil;
import com.baizhi.dao.UserDao;
import com.baizhi.dto.Result;
import com.baizhi.dto.UserResponse;
import com.baizhi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @PostMapping("/code")
    public Result code(String phone, HttpServletRequest request) {
        String code = RandomUtil.randomNumbers(6);
        HttpSession session = request.getSession();
        session.setAttribute(phone, code);
        log.debug("code:{}", code);
        return new Result().ok();
    }

    @PostMapping("/login")
    private Result login(@RequestBody UserResponse userResponse, HttpServletRequest request) {
        Result login = null;
        HttpSession session = request.getSession();
        log.debug("userResponse:{}", userResponse);
        if (userResponse.getCode() != null) {
            Object code = session.getAttribute(userResponse.getPhone());
            if (userResponse.getCode().equalsIgnoreCase(code.toString())) {
                login = userService.login(userResponse.getPhone(), null, true);
                session.setAttribute("login", true);
                session.setAttribute("user", login.getData());
//            session.removeAttribute("code");
                return login;
            }
            return new Result().error(userResponse);
        }
        login = userService.login(userResponse.getPhone(), userResponse.getPassword(), false);
        if (login.getSuccess()) {
            session.setAttribute("login", true);
            session.setAttribute("user", login.getData());
        }
        return login;
    }
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        HttpSession session = request.getSession();
//        session.removeAttribute("login");
//        session.removeAttribute("");
        session.invalidate();
        return new Result().ok();
    }
    @GetMapping("/me")
    public Result me(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        return new Result().ok(user);
    }
}