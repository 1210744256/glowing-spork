package com.baizhi.controller;

import com.baizhi.entity.City;
import com.baizhi.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Map;

@Controller
@EnableWebMvc
@RequestMapping("/city")
@ResponseBody
public class CityController {
        Logger logger = LoggerFactory.getLogger(CityController.class);
    @Autowired
    private CityService cityService;
    @RequestMapping("/query")
    public List<City> query(@RequestBody City city){
        List<City> select = cityService.select(city);
        return select;
    }
    @RequestMapping("/deleteById")
    public void deleteById(int id){
        cityService.deleteById(id);
    }
    @RequestMapping("/insert")
    public void insert(@RequestBody City city){
        cityService.insert(city);
    }
    @RequestMapping("/selectLimit")
    public Map<String,Object> selectLimit(int page,int size){
        logger.debug("page:"+page);
        return cityService.selectLimit(page,size);
    }
}
