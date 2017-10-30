package com.hsu.netchat.bean;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;

public class ChatMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	//存放上线的用户列表
	private List <String> names;
	//要发送的信息
	private String content;
	//发送信息的类型,单聊与白板演示
	private Integer type;
	//消息是从哪个好友发来的
	private String from;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String toJson(){
		return gson.toJson(this);
	}
}
