package com.baizhi.dao;

import com.baizhi.entity.Sign;

import java.util.List;

public interface SignDao extends BaseDao<Sign,Integer>{
    List<Sign> queryAllOfDate();
}
