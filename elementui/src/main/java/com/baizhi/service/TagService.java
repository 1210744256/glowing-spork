package com.baizhi.service;

import com.baizhi.dto.TagResponse;
import com.baizhi.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> queryAll();
    void deleteById(int id);
    void insert(Tag tag);
    List<Tag> queryTypeAll(String type);
    List<TagResponse> queryEchas();
}
