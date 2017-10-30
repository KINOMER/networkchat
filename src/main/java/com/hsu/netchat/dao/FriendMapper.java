package com.hsu.netchat.dao;

import com.hsu.netchat.bean.Friend;
import com.hsu.netchat.bean.FriendExample;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface FriendMapper {
    long countByExample(FriendExample example);

    int deleteByExample(FriendExample example); 

    int deleteByPrimaryKey(Integer id);

    int insert(Friend record);

    int insertSelective(Friend record); 

    List<Friend> selectByExample(FriendExample example);

    Friend selectByPrimaryKey(Integer id); 

    int updateByExampleSelective(@Param("record") Friend record, @Param("example") FriendExample example);

    int updateByExample(@Param("record") Friend record, @Param("example") FriendExample example);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);

	int isFriend(int userId, int friendId, int friendId2, int userId2);

	Set<Integer> getFriendIds(int userId, int userId2);
}