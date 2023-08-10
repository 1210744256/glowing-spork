package com.baizhi.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Shop {
    private Integer id;
    private String name;
    private Integer typeId;
    private String images;
    private String area;
    private String address;
    private Double x;
    private Double y;
    private Integer avgPrice;
    private Integer sold;
    private Integer comments;
    private Integer score;
    private String openHours;
    private Date createTime;
    private Date updataTime;

}
