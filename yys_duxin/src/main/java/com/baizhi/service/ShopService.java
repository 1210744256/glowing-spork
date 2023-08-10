package com.baizhi.service;


import com.baizhi.dto.Result;
import com.baizhi.dto.ShopRequest;
import com.baizhi.entity.Shop;

import java.util.List;

public interface ShopService {
    Result selectById(int id);
    Result queryByType(ShopRequest shopRequest);
    Result queryByName(String name);
   Result queryByScoreLimit(int startIndex,int size);
    Result queryAllSlide();
}
