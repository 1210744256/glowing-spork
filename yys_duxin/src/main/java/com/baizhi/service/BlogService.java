package com.baizhi.service;

import com.baizhi.dto.Result;

public interface BlogService {
    Result queryByLimitForMe(Integer userId,Integer page,Integer size);
}
