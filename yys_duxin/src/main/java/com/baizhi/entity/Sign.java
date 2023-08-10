package com.baizhi.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Sign {
//    CREATE TABLE `tb_sign` (
//  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
//  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
//  `year` year(4) NOT NULL COMMENT '签到的年',
//  `month` tinyint(2) NOT NULL COMMENT '签到的月',
//  `date` date NOT NULL COMMENT '签到的日期',
//  `is_backup` tinyint(1) unsigned DEFAULT NULL COMMENT '是否补签',
//  PRIMARY KEY (`id`) USING BTREE
//) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
    private Integer id;
    private Integer userId;
    private Integer year;
    private Integer month;
    private Date date;
    private Integer isBackup;
}
