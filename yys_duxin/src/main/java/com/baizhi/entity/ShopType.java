package com.baizhi.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ShopType {
    private Integer id;
    private String name;
    private String icon;
    private Integer sort;
    private Date createTime;
    private Date updateTime;
}
