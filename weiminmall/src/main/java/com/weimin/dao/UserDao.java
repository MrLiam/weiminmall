package com.weimin.dao;

import com.weimin.domain.User;


public interface UserDao {
    public int deleteByPrimaryKey(String userid);

    public int insert(User record);

    public int insertSelective(User record);

    public User selectByPrimaryKey(String userid);

    public int updateByPrimaryKeySelective(User record);

    public int updateByPrimaryKey(User record);
}