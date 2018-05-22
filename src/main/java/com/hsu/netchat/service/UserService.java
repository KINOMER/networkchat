package com.hsu.netchat.service;

import com.hsu.netchat.bean.User;

public interface UserService {

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 */
	void updateUserInfo(User user);

	/**
	 * 根据用户名获取获取用户所有信息
	 * 
	 * @param username
	 * @return
	 */
	User getUserByUserName(String username);

	/**
	 * 上传用户头像
	 * 
	 * @param user
	 */
	void uploadAdator(User user);
	
	/**
	 * 根据用户的id获取用户信息
	 * 
	 * @param id 用户id
	 * @return
	 */
	User getUserById(Integer id);
	
	/**
	 * 用户注册
	 * 
	 * @param user
	 */
	void insertUser(User user);


}
