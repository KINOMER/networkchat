package com.hsu.netchat.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsu.netchat.bean.Friend;
import com.hsu.netchat.bean.Msg;
import com.hsu.netchat.bean.User;
import com.hsu.netchat.service.FriendService;
import com.hsu.netchat.service.UserService;

@Controller
public class FriendController {

	@Autowired
	private UserService userService;
	@Autowired
	private FriendService friendService;
	
	@RequestMapping("/addFriend")
	@ResponseBody
	public Msg addFriend(HttpServletRequest request,String friendName){
		User friend = userService.getUserByUserName(friendName);
		User user = (User) request.getSession().getAttribute("userInfo");
		
		if(friend == null){
			return Msg.fail("该用户不存在！");	
		}else if(user != null && friend != null){
			if(user.getUsername().equals(friend.getUsername())){
				return Msg.fail("您不能添加自己为好友哦！");	
			}else{
				Friend friendReal = new Friend();
				int userId = user.getUserId();
				int friendId = friend.getUserId();
				
				friendReal.setUserId(userId);
				friendReal.setFriendId(friendId);
				
				// 在添加好友之前先判断是不是好友关系，不是好友关系才能添加好友
				boolean b = friendService.isFriend(userId,friendId,friendId,userId);
				if(b){ 
					friendService.addFriend(friendReal);
					return Msg.success("添加好友成功!");
				}else{
					return Msg.fail("你和"+friendName+"已经是好友关系!");	
				}
			}
		}else{
			return Msg.fail("您还没有登录！");	
		}
	}
    
	@RequestMapping("/getFriendsList")
	@ResponseBody
	public Msg getFriendsList(HttpServletRequest request){
		
		User user = (User)request.getSession().getAttribute("userInfo");
		Map <String ,User> map = new HashMap<String ,User>();
		
		if(user != null){
			int userId = user.getUserId();
			// 获得所有好友的id集合
			Set<Integer> set = friendService.getFriendIdList(userId,userId);
			// 根据好友的id集合获得好友信息，并将好友信息以json形式返回
			for(Integer id : set){
				User friend = userService.getUserById(id);
				// 将好友的密码设置为空
				friend.setPassword("");
				map.put(friend.getUsername(), friend);
			}
		}
		
		return Msg.success("获取朋友列表成功！").add("friends", map);
	}
}
