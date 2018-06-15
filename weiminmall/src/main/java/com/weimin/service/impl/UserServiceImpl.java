package com.weimin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weimin.dao.UserDao;
import com.weimin.domain.User;
import com.weimin.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	
	@Override
	public User getUserById(String userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}
}
