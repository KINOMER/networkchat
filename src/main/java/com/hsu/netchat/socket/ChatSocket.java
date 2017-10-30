package com.hsu.netchat.socket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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


@ServerEndpoint("/chatSocket")
public class ChatSocket {

	private String username;
	private static Gson gson = new Gson();
	//用于将用户名和session进行绑定
	private static Map<String,Session> map = new HashMap<String,Session>();

	@OnOpen
	public void open(Session session){
		String queryString = session.getQueryString();
		try {
			//转换乱码
			username = URLDecoder.decode(queryString, "utf-8");
			username = username.split("=")[1];
			System.out.println(username+"....已经获得连接");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		map.put(username, session);
	}

	/**
	 * 处理接收到的消息
	 */
	@OnMessage
	public void message(Session session,String msg){
		SingleChat singleChat = gson.fromJson(msg, SingleChat.class);
		ChatMessage chatMessage = null;
		DrawBoardMessage drawBoardMessage = null;
		
		if(singleChat != null){
			Integer type = singleChat.getType();
			String to = singleChat.getTo();
			//和好友之间单聊信息
			if(type == 1){
				chatMessage = new ChatMessage();
				
				chatMessage.setContent(singleChat.getMsg());
				chatMessage.setFrom(this.username);
				chatMessage.setType(1);
				//通过获得发往的好友名在map中获得该好友的session通道
				Session to_singleSession = map.get(to);
				//如果用户在线
				if(to_singleSession != null){
					try {
						//将要发送的消息以json数据形式发送
						to_singleSession.getBasicRemote().sendText(chatMessage.toJson());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{ //用户不在线时将好友发送的消息放在数据库中，在用户登录时获取信息
					System.out.println(to+"不在线..........不要再说话了");
				}
				
			}else if(type == 0) { //单个好友间的白板演示
				drawBoardMessage = new DrawBoardMessage();
				
				drawBoardMessage.setContent(singleChat.getMsg());
				drawBoardMessage.setFrom(this.username);
				drawBoardMessage.setType(0);
				drawBoardMessage.setLineColor(singleChat.getLineColor());
				drawBoardMessage.setLineWidth(singleChat.getLineWidth());
				//根据好友名字获得对应的信息通道
				Session to_singleSession = map.get(to);
				if(to_singleSession != null){
					try {
						//将要发送的消息以json数据形式发送
						to_singleSession.getBasicRemote().sendText(drawBoardMessage.toJson());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					System.out.println(to+"不在线..........不要再画画了");
				}
			}else if(type == 100){   //画图起点更换时，重新划线，使线段不连续
				drawBoardMessage = new DrawBoardMessage();
				drawBoardMessage.setType(100);
				//根据好友名字获得对应的信息通道
				Session to_singleSession = map.get(to);
				if(to_singleSession != null){
					try {
						//将要发送的消息以json数据形式发送
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
	 * @param session 用户的session
	 */
	@OnClose
	public  void close(Session session){
		try{
			map.remove(this.username);
			System.out.println(this.username+"....下线了");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@OnError
	public void error(Session session, Throwable t){
		t.printStackTrace();
	}
	/**
	 *  将信息进行广播给所有人
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
}
