package com.yj.dao;

import com.yj.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User login(User user);

}
