package com.baizhi.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baizhi.dao.SignDao;
import com.baizhi.dao.UserDao;
import com.baizhi.dto.Result;
import com.baizhi.dto.UserRequest;
import com.baizhi.dto.UserResponse;
import com.baizhi.entity.Sign;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

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
    private Result login(@RequestBody UserRequest userResponse, HttpServletRequest request) {
        Result login = null;
        HttpSession session = request.getSession();
        log.debug("userResponse:{}", userResponse);
        if (!StrUtil.isEmpty(userResponse.getCode())) {
            Object code = session.getAttribute(userResponse.getPhone());
            if (userResponse.getCode().equalsIgnoreCase(code.toString())) {
                login = userService.login(userResponse.getPhone(), null, true);
                session.setAttribute("login", true);
                session.setAttribute("user", login.getData());
//            session.removeAttribute("code");
                return login;
            }
            return new Result().error(userResponse,"验证码错误");
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
        UserResponse userResponse=new UserResponse();
        BeanUtils.copyProperties(user,userResponse);
        return new Result().ok(userResponse);
    }
    @PostMapping("/sign")
    public Result insertSign(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        Calendar calendar=Calendar.getInstance();
        Sign sign=new Sign();
        sign.setUserId(user.getId());
        sign.setYear(calendar.get(Calendar.YEAR));
        sign.setMonth(calendar.get(Calendar.MONTH)+1);
        sign.setDate(calendar.getTime());
        userService.insertSign(sign);
        return new Result().ok();
    }
    @GetMapping("/sign/count")
    public Result querySignCount(){
        Integer integer = userService.SignCount();
        return new Result().ok(integer);
    }

}
