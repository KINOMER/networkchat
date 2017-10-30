package com.hsu.netchat.service;

import java.util.Set;

import com.hsu.netchat.bean.Friend;

public interface FriendService {

	/**
	 * 添加好友
	 * @param friendReal
	 */
	void addFriend(Friend friendReal);
	
	/**
	 * 添加好友之前判断这两个用户是不是好友，两边好友都可以判断
	 * @param friendId  好友id
	 * @param userId   用户id
	 * @return
	 */
	boolean isFriend(int userId, int friendId, int friendId2, int userId2);

	/**
	 * 获取好友的id列表
	 * @return
	 */
	Set<Integer> getFriendIdList(int userId, int userId2);

}
