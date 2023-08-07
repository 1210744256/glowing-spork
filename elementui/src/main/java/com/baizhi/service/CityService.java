package com.baizhi.service;

import com.baizhi.entity.City;

import java.util.List;
import java.util.Map;

public interface CityService {
    List<City> select(City city);

    void insert(City city);

    void deleteById(int id);

    Map<String,Object> selectLimit(int page, int size);
}
