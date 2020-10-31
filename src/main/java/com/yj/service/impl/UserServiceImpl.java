package com.yj.service.impl;

import com.yj.dao.UserDao;
import com.yj.entity.User;
import com.yj.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User login(User user) {
        User userDB = userDao.login(user);
        if(userDB!=null){
            return userDB;
        }

        throw  new RuntimeException("认证失败！");
    }
}
