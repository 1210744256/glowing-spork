package com.baizhi.controller;

import com.baizhi.dto.Result;
import com.baizhi.dto.ShopRequest;
import com.baizhi.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @GetMapping("/{shopId}")
    public Result queryById(@PathVariable String shopId){
        log.debug("shopId{}:"+shopId);
        return shopService.selectById(Integer.parseInt(shopId));
    }
    @GetMapping("/of/type")
    public Result queryByType(ShopRequest shopRequest){
        log.debug("shopRequest:{}"+shopRequest);
        return shopService.queryByType(shopRequest);
    }
    @GetMapping("/of/name")
    public Result queryByName(String name){
        return shopService.queryByName(name);
    }
    @GetMapping("/hot/top4")
    public Result queryByScoreLimit(){
        return shopService.queryByScoreLimit(0,4);
    }
//     Result queryAllSlide();
    @GetMapping("/autoplay")
    public  Result queryAllSlide(){
        return shopService.queryAllSlide();
    }
}
