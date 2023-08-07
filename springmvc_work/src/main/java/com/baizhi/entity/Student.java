package com.baizhi.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @ExcelProperty(value = "学生id",index = 0)
    private Integer id;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String name;
    @ExcelProperty(value = "年龄",index = 2)
    private Integer age;
    @ExcelProperty(value = "qq号",index = 3)
    private String qq;
    @ExcelProperty(value = "手机号",index = 4)
    private String phone;
    @ExcelProperty(value = "生日",index = 5)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bir;
    @ExcelProperty(value = "星座",index = 6)
    private String starts;
    @ExcelProperty(value = "属相",index = 7)
    private String attr;
    @ExcelProperty(value = "备注",index = 8)
    private String mark;
    @ExcelIgnore
    private Clazz clazz;
    @ExcelIgnore
    private Group group;
    @ExcelIgnore
    private City city;
    @ExcelIgnore
    private List<Tag> tags;
}
