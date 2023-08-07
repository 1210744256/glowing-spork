package com.baizhi.service.impl;

import com.baizhi.dao.CityDao;
import com.baizhi.entity.City;
import com.baizhi.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<City> select(City city) {
        List<City> select = cityDao.select(city);
        return select;
    }

    @Override
    public void insert(City city) {
        cityDao.insert(city);
    }

    @Override
    public void deleteById(int id) {
        cityDao.deleteById(id);
    }

    @Override
    public Map<String,Object> selectLimit(int page, int size) {
        Map<String,Object> map=new HashMap<>();
        System.out.println("page:"+page);
        int count = cityDao.queryAllPage();
        int totalPage=0;
        if(count%size==0){
          totalPage=count/size;
        }else {
            totalPage=count/size+1;
        }
        map.put("totalPage",totalPage);
        List<City> cities = cityDao.selectLimit(new City(), (page - 1) * size, size);
        map.put("citys",cities);
        return map;
    }
}
