package com.baizhi.dao;

import com.baizhi.dto.ShopRequest;
import com.baizhi.entity.Shop;
import com.baizhi.entity.ShopSlide;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao extends BaseDao<Shop,Integer> {
        List<Shop> queryByType(@Param("shopRequest") ShopRequest shopRequest,
                               @Param("startIndex") Integer startIndex,@Param("size") Integer size);
        List<Shop> queryByName(String name);
//        根据评分进行排序并分页
        List<Shop> queryByScoreLimit(@Param("startIndex") Integer startIndex,@Param("size") Integer size);
        List<ShopSlide> queryAllSlide();
}
