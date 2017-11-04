package com.hsu.netchat.service;

import com.hsu.netchat.bean.ChatRecord;

public interface ChatRecordService {
	
	/**
	 * 好友不在线时，将发送的消息存放在数据库中
	 * @param chatRecord
	 */
	void insertRecord(ChatRecord chatRecord);

}
