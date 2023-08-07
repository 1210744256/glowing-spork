package com.baizhi.dao;

import com.baizhi.entity.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityDao {
    List<City> select(City city);
    void insert(City city);
    void deleteById(int id);
    int queryAllPage();
    List<City> selectLimit(@Param("city") City city,@Param("startIndex")int startIndex,
                           @Param("size")int size);
}
