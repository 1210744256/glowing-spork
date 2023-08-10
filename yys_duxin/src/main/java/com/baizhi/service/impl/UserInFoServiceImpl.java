package com.baizhi.service.impl;

import com.baizhi.dao.UserInFoDao;
import com.baizhi.dto.Result;
import com.baizhi.entity.UserInFo;
import com.baizhi.service.UserInFoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInFoServiceImpl implements UserInFoService {
    @Autowired
    private UserInFoDao userInFoDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Result selectById(int id) {
        UserInFo userInFo = userInFoDao.selectById(id);
        return new Result().ok(userInFo);
    }
}
