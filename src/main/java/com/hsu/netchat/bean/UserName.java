package com.hsu.netchat.bean;

import java.util.List;

import com.google.gson.Gson;

public class UserName {
	private Integer type;
	private List<String> list;
	private Gson gson = new Gson();
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	
	public String toJson(){
		return gson.toJson(this);
	}
}
