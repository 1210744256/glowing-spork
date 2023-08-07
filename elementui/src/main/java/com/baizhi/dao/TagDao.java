package com.baizhi.dao;

import com.baizhi.dto.TagResponse;
import com.baizhi.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagDao {
    List<Tag> queryAll();
    void deleteById(int id);
    void insert(Tag tag);
    Tag queryById(int id);
    List<Tag> queryTypeAll(String type);
    List<Tag> queryByIds(@Param("ids") List<Integer> ids);
//    查询出标签名以及标签的占比
    List<TagResponse> queryEchas();
}
