package com.baizhi.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
    private Integer id;
    private Integer shopId;
    private Integer userId;
    private String title;
    private String images;
    private String content;
    private Integer liked;
    private Integer comments;
    private Date createTime;
    private Date updateTime;
}
