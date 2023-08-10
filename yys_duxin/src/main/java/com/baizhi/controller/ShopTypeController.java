package com.baizhi.controller;

import com.baizhi.dto.Result;
import com.baizhi.service.ShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop-type")
public class ShopTypeController {
    @Autowired
    private ShopTypeService shopTypeService;
    @GetMapping("/list")
    public Result queryAll(){
        return shopTypeService.selectAll();
    }
}
