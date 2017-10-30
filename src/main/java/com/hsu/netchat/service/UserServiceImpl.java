package com.hsu.netchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsu.netchat.bean.User;
import com.hsu.netchat.dao.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Override
	public void updateUserInfo(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}
	@Override
	public User getUserByUserName(String username) {
		return userMapper.getUserByUserName(username);
	}
	@Override
	public void uploadAdator(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}
	@Override
	public User getUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
	@Override
	public void insertUser(User user) {
		
		userMapper.insertSelective(user);
	}

}
