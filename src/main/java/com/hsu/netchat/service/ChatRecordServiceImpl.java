package com.hsu.netchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsu.netchat.bean.ChatRecord;
import com.hsu.netchat.dao.ChatRecordMapper;

@Service
public class ChatRecordServiceImpl implements ChatRecordService {

	@Autowired
	private ChatRecordMapper chatRecordMapper;
	@Override
	public void insertRecord(ChatRecord chatRecord) {
		chatRecordMapper.insertSelective(chatRecord);
	}

}
