package com.hsu.netchat.socket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.hsu.netchat.bean.ChatMessage;
import com.hsu.netchat.bean.DrawBoardMessage;
import com.hsu.netchat.bean.SingleChat;
import com.hsu.netchat.bean.UserName;


@ServerEndpoint("/chatSocket")
public class ChatSocket {

	private String username;
	private static Gson gson = new Gson();
	/** 用于将用户名和session进行绑定 */
	private static Map<String, Session> map = new HashMap<String, Session>();
	private static List<String> userNameList = new ArrayList<String>();

    /**
     * 当有Socket 连接时，对应的方法处理
     * 
     * @param session
     */
	@OnOpen
	public void open(Session session){
		String queryString = session.getQueryString();
		
		try {
			// 转换乱码，获得当前上线用户的用户名
			username = URLDecoder.decode(queryString, "utf-8");
			username = username.split("=")[1];
			//System.out.println(username+"....已经获得连接");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  

		// 将用户名与对应的Session 通道存在map 中
		map.put(username, session);
		// 将上线的用户存放在集合中
		userNameList.add(this.username);
		// 进行广播，通知其他在线的人(必须要有好友关系)有人上线
		broadcast(map,userNameList);
	}

    /**
     * 处理好友之间的通信
     * 
     * @param session
     * @param msg
     */
	@OnMessage
	public void message(Session session, String msg){
	    // 将接收到的JSON 数据与SingleChat 对象中的字段进行映射
		SingleChat singleChat = gson.fromJson(msg, SingleChat.class);
		ChatMessage chatMessage = null;
		DrawBoardMessage drawBoardMessage = null;

		if(singleChat != null){
			/** type 为1 时，表示进行聊天，为0 时，表示进行白板演示，为100 时，表示断开白板演示，使线段不连续*/
			Integer type = singleChat.getType();
			String to = singleChat.getTo();
			
			if(type == 1){
				chatMessage = new ChatMessage();

				chatMessage.setContent(singleChat.getMsg());
				chatMessage.setFrom(this.username);
				chatMessage.setType(1);
				// 通过获得发往的好友名在map中获得该好友的session通道
				Session to_singleSession = map.get(to);
				// 如果用户在线
				if(to_singleSession != null){
					try {
						// 将要发送的消息以json数据形式发送
						to_singleSession.getBasicRemote().sendText(chatMessage.toJson());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					//System.out.println(to+"不在线..........不要再说话了");
				}

			}else if(type == 0) { 
				drawBoardMessage = new DrawBoardMessage();

				drawBoardMessage.setContent(singleChat.getMsg());
				drawBoardMessage.setFrom(this.username);
				drawBoardMessage.setType(0);
				drawBoardMessage.setLineColor(singleChat.getLineColor());
				drawBoardMessage.setLineWidth(singleChat.getLineWidth());
				// 根据好友名字获得对应的信息通道
				Session to_singleSession = map.get(to);
				
				if(to_singleSession != null){
					try {
						// 将要发送的消息以json数据形式发送
						to_singleSession.getBasicRemote().sendText(drawBoardMessage.toJson());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					//System.out.println(to+"不在线..........不要再画画了");
				}
			}else if(type == 100){
				drawBoardMessage = new DrawBoardMessage();
				drawBoardMessage.setType(100);
				// 根据好友名字获得对应的信息通道
				Session to_singleSession = map.get(to);
				
				if(to_singleSession != null){
					try {
						// 将要发送的消息以json数据形式发送
						to_singleSession.getBasicRemote().sendText(drawBoardMessage.toJson());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 用户断开webSocket连接之后
	 * 
	 * @param session 用户的session
	 */
	@OnClose
	public  void close(Session session){
		try{
			userNameList.remove(this.username);
			map.remove(this.username);
			// 某个用户下线的时候，同样通知对应的好友，改变当前好友的在线信息状况
			broadcast(map,userNameList);
			// System.out.println(this.username + "....下线了");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@OnError
	public void error(Session session, Throwable t){
		t.printStackTrace();
	}
	
	/**
	 *  将信息进行广播给所有人，可用于群聊
	 *  
	 * @param session 传入的sessions
	 * @param msg 传入返回的json字符串
	 */
	public void broadcast (List<Session>session,String msg){
		for(Iterator<Session> iterator = session.iterator() ; iterator.hasNext();){
			Session ss = iterator.next();
			try {
				ss.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更新用户的在线状态，及时发布给好友
	 * 
	 * @param map
	 * @param userNameList
	 */
	public void broadcast (Map<String, Session> map, List<String> userNameList){
		Iterator<Map.Entry<String, Session>> entries = map.entrySet().iterator(); 

		while (entries.hasNext()) { 
			Map.Entry<String, Session> entry = entries.next(); 
			Session to_singleSession = entry.getValue();
			UserName username = new UserName();

			username.setType(101);
			username.setList(userNameList);
			try {
				to_singleSession.getBasicRemote().sendText(username.toJson());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
