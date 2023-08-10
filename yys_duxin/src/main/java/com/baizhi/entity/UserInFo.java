package com.baizhi.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserInFo {
    private Integer userId;
    private String city;
    private String introduce;
    private Integer fans;
    private Integer followee;
    private Integer gender;
    private Date birthday;
    private Integer credits;
    private Integer level;
    private Date createTime;
    private Date updateTime;
}
