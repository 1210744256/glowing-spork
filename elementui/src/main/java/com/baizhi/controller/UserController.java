package com.baizhi.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@EnableWebMvc
@RequestMapping("/user")
@ResponseBody
public class UserController {
        Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @RequestMapping("/regist")
    public Map<String,Object> regist(@RequestBody User user,HttpServletRequest request){
        Map<String, Object> regist = userService.regist(user);
//        if(regist.get("message").toString().equals("注册成功")){
//            Map<String, Object> login = login(,user, request);
//            login.put("message","注册成功");
//            return login;
//        }
        return regist;
    }
    @RequestMapping("/login")
    public Map<String,Object> login(String captcha,@RequestBody User user, HttpServletRequest request){
        Map<String, Object> login = userService.login(user);
        logger.debug("captcha:"+captcha);
        HttpSession session = request.getSession();
        Object captcha1 = session.getAttribute("captcha");
        if(!captcha1.toString().equals(captcha)){
            login.put("message","验证码错误");
            return login;
        }
        if(login.get("message").toString().equals("success")){
            session.setAttribute("login","success");
//            session.setAttribute("user",login.get("user"));
        }
        return login;
    }
    @RequestMapping("/captcha")
    public void capcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
//        验证码对应的String类型
        String code = lineCaptcha.getCode();
//        将验证码传到session中并输出验证码
        HttpSession session = request.getSession();
        session.setAttribute("captcha",code);
        lineCaptcha.write(response.getOutputStream());
    }
    //    退出登录
    @RequestMapping("/esc")
    public void esc(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("login");
    }
    @RequestMapping("/queryAll")
    public List<User> queryAll(){
        return userService.queryAll();
    }
    @RequestMapping("/insertOrUpdate")
    public void insertOrUpdate(@RequestBody User user){
        if(user.getId()==null){
            userService.regist(user);
        }else {
            userService.update(user);
        }
    }
    @RequestMapping("/deleteById")
    public void deleteById(int id){
        userService.deleteById(id);
    }
    @RequestMapping("/selectLimit")
    public Map<String, Object> selectLimit(int page,int size){
        return userService.selectLimit(page,size);
    }
}
