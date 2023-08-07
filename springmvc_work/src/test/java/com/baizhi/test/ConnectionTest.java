package com.baizhi.test;

import com.alibaba.excel.EasyExcel;
import com.baizhi.dao.CityDao;
import com.baizhi.dao.StudentDao;
import com.baizhi.dao.StudentDataDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.City;
import com.baizhi.entity.Clazz;
import com.baizhi.entity.Student;
import com.baizhi.entity.User;
import com.baizhi.listener.StudentDataListener;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ConnectionTest {
    @Test
    public void connetTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);
        List<User> select = userDao.select(new User());
        select.stream().forEach(user -> {
            System.out.println(user);
            userDao.insert(user);
        });
    }

    @Test
    public void conn2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        CityDao cityDao = context.getBean("cityDao", CityDao.class);
        List<City> select = cityDao.select(new City());
        select.stream().forEach(city -> {
            System.out.println(city);
            cityDao.insert(city);
        });
    }

    @Test
    public void conn3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
        List<Student> students = studentDao.selectLimit(new Student(), 0, 6);
        students.forEach(student -> {
//            System.out.println(student);
            studentDao.insert(student);
        });
    }
    @Test
    public void conn4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
        Student student1 = new Student();
        Clazz clazz = new Clazz();
        clazz.setId(11);
        student1.setClazz(clazz);
        List<Student> students = studentDao.select(new Student());
        students.forEach(student -> {
            System.out.println(student);
//            studentDao.insert(student);
        });
    }
    @Test
    public void sak(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        StudentDataDao studentDataDao = context.getBean("studentDataDao", StudentDataDao.class);
        String fileName = "F:\\学生.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(fileName, Student.class, new StudentDataListener(studentDataDao)).sheet().doRead();
    }
}
