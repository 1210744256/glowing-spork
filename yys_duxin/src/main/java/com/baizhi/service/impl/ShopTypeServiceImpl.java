package com.baizhi.service.impl;

import com.baizhi.dao.ShopTypeDao;
import com.baizhi.dto.Result;
import com.baizhi.service.ShopTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class ShopTypeServiceImpl implements ShopTypeService {
    @Autowired
    private ShopTypeDao shopTypeDao;
    @Override
    public Result selectAll() {
        return new Result().ok(shopTypeDao.selectAll());
    }
}
