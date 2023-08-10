package com.baizhi.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baizhi.dao.ShopDao;
import com.baizhi.dto.Result;
import com.baizhi.dto.ShopRequest;
import com.baizhi.entity.Shop;
import com.baizhi.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Result selectById(int id) {
        return new Result().ok(shopDao.selectById(id));
    }

    @Override
    public Result queryByType(ShopRequest shopRequest) {
        int startIndex=0;
        int size=2;
//        if(ObjectUtil.isEmpty(shopRequest.getCurrent())){
//
//        }
        List<Shop> shops = shopDao.queryByType(shopRequest, startIndex, size);
        return new Result().ok(shops);
    }

    @Override
    public Result queryByName(String name) {
        return new Result().ok(shopDao.queryByName(name));
    }
}
