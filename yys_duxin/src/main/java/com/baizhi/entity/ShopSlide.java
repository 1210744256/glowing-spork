package com.baizhi.entity;

import lombok.Data;

import java.time.Period;
import java.util.Date;

@Data
public class ShopSlide {
    private Integer id;
    private String alt;
    private String url;
    private Integer status;
    private Integer sortOrder;
    private Integer shopId;
    private Date created;
    private Date updated;
}
