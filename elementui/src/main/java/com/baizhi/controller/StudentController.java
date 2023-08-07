package com.baizhi.controller;

import com.alibaba.excel.EasyExcel;
import com.baizhi.entity.Student;
import com.baizhi.service.ClazzService;
import com.baizhi.service.StudentService;
import com.baizhi.util.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@EnableWebMvc
@RequestMapping("/student")
@ResponseBody
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClazzService clazzService;

    @RequestMapping("/selectLimit")
    public Map<String, Object> selectLimit(int page, int size, @RequestBody Student student) {
        logger.debug("student:" + student);
        return studentService.selectLimit(student, page, size);
    }

    @RequestMapping("/select")
    public List<Student> select(@RequestBody Student student) {
//        logger.info("id查student:"+student);
        return studentService.select(student);
    }

    //    根据小组id查询学生人数
    @RequestMapping("/selectGroupStudentCount")
    public int selectGroupStudentCount(int id) {
        return studentService.selectGroupStudentCount(id);
    }

    //    添加学生
    @RequestMapping("/insert")
    public void insert(@RequestBody Student student) {
        Date bir = student.getBir();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bir);
        student.setAge(DateUtil.getYear(bir));
        student.setStarts(DateUtil.XingZuo
                (calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH + 1), calendar.get(Calendar.DATE)));
        student.setAttr(DateUtil.ShuXiang(calendar.get(Calendar.YEAR)));
        if (student.getMark() == null) student.setMark("该生活泼开朗");
        logger.debug("添加Student:" + student);
        studentService.insert(student);
//        添加学生后绘制图表
        Map<String, Object> map = clazzService.clazzExcel();
        System.out.println(map);
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(json);
        GoEasy goEasy = new GoEasy("https://rest-hz.goeasy.io", "BC-3618fc7f5fb940568c6727366eec1bb1");
        goEasy.publish("student_channel", json, new PublishListener() {
            @Override
            public void onSuccess() {
                System.out.println("Publish success.");
            }

            @Override
            public void onFailed(GoEasyError error) {
                System.out.println("Failed to Publish message, error:" + error.getCode() + " , " + error.getContent());
            }
        });
    }

    //    修改学生
    @RequestMapping("/update")
    public void update(@RequestBody Student student) {
        Date bir = student.getBir();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bir);
        student.setAge(DateUtil.getYear(bir));
        student.setStarts(DateUtil.XingZuo
                (calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH + 1), calendar.get(Calendar.DATE)));
        student.setAttr(DateUtil.ShuXiang(calendar.get(Calendar.YEAR)));
        if (student.getMark() == null) student.setMark("该生活泼开朗");
        studentService.update(student);
        //        修改学生后绘制图表
        Map<String, Object> map = clazzService.clazzExcel();
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        GoEasy goEasy = new GoEasy("https://rest-hz.goeasy.io", "BC-3618fc7f5fb940568c6727366eec1bb1");
        goEasy.publish("student_channel", json, new PublishListener() {
            @Override
            public void onSuccess() {
                System.out.println("Publish success.");
            }

            @Override
            public void onFailed(GoEasyError error) {
                System.out.println("Failed to Publish message, error:" + error.getCode() + " , " + error.getContent());
            }
        });
    }

    //    删除学生
    @RequestMapping("/delete")
    public void delete(int id) {
        studentService.deleteById(id);
        //        删除学生后绘制图表
        Map<String, Object> map = clazzService.clazzExcel();
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        GoEasy goEasy = new GoEasy("https://rest-hz.goeasy.io", "BC-3618fc7f5fb940568c6727366eec1bb1");
        goEasy.publish("student_channel", json, new PublishListener() {
            @Override
            public void onSuccess() {
                System.out.println("Publish success.");
            }

            @Override
            public void onFailed(GoEasyError error) {
                System.out.println("Failed to Publish message, error:" + error.getCode() + " , " + error.getContent());
            }
        });
    }
//    下载学生信息
    @RequestMapping("/down")
    public void down(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Student.class).sheet("模板").doWrite(studentService.select(new Student()));
//        EasyExcel.write(fileName, Student.class).sheet("模板").doWrite(studentService.select(new Student()));
    }


}
