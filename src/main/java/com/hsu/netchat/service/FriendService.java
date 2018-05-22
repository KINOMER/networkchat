package com.hsu.netchat.service;

import java.util.Set;

import com.hsu.netchat.bean.Friend;

public interface FriendService {

	/**
	 * 添加好友
	 * 
	 * @param friendReal
	 */
	void addFriend(Friend friendReal);

	/**
	 * 添加好友之前判断这两个用户是不是好友，两边好友都可以判断
	 * 
	 * @param userId
	 * @param friendId
	 * @param friendId2
	 * @param userId2
	 * @return
	 */
	boolean isFriend(int userId, int friendId, int friendId2, int userId2);

	/**
	 * 获取所有好友的id 集合
	 * 
	 * @param userId
	 * @param userId2
	 * @return
	 */
	Set<Integer> getFriendIdList(int userId, int userId2);

}
