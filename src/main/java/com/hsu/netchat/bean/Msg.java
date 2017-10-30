package com.hsu.netchat.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Msg implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//状态码，1表示成功，0表示失败
	private int code;
	//提示信息
	private String msg;
	//用户返回给浏览器的数据
	private Map<String,Object> map = new HashMap<>();
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public static Msg success(String msgInfo){
		Msg msg = new Msg();
		msg.setCode(1);
		msg.setMsg(msgInfo);
		return msg;
	}
	
	public static Msg fail(String msgInfo){
		Msg msg = new Msg();
		msg.setCode(0);
		msg.setMsg(msgInfo);
		return msg;
	}
	
	public Msg add(String key,Object value){
		this.getMap().put(key, value);
		return this;
	}
}
