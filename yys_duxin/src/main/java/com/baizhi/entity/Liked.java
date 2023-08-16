package com.baizhi.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Liked {
    private Integer id;
    private Integer userId;
    private Integer blogId;
    private Integer liked;
    private Date createTime;
    private Date updateTime;
}
