package com.baizhi.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String phone;
    private String password;
    private String nickName;
    private String icon;
    private Date createTime;
    private Date updateTime;
}
