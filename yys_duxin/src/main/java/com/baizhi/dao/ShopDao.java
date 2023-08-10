package com.baizhi.dao;

import com.baizhi.dto.ShopRequest;
import com.baizhi.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao extends BaseDao<Shop,Integer> {
        List<Shop> queryByType(@Param("shopRequest") ShopRequest shopRequest,
                               @Param("startIndex") Integer startIndex,@Param("size") Integer size);
        List<Shop> queryByName(String name);
}
