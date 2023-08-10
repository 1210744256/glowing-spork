package com.baizhi.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Follow {
    private Integer id;
    private Integer userId;
    private Integer followUserId;
    private Date createTime;
}
