package com.hsu.netchat.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsu.netchat.bean.Friend;
import com.hsu.netchat.dao.FriendMapper;

@Service
public class FriendServiceImpl implements FriendService {
	
	@Autowired
	private FriendMapper friendMapper;
	
	@Override
	public void addFriend(Friend friendReal) {
		
		friendMapper.insert(friendReal);
	}
	@Override
	public boolean isFriend(int userId, int friendId, int friendId2, int userId2) {
		
		int total = friendMapper.isFriend(userId, friendId,friendId2,userId2);
		if(total>=1){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public Set<Integer> getFriendIdList(int userId, int userId2) {
		// TODO Auto-generated method stub
		return friendMapper.getFriendIds(userId,userId2);
	}

}
