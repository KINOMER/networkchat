package com.hsu.netchat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsu.netchat.bean.Anno;
import com.hsu.netchat.dao.AnnoMapper;

@Service
public class AnnoServiceImpl implements AnnoService {
	@Autowired
	private AnnoMapper annoMapper;
	
	@Override
	public List<Anno> getAnnoMsg() {
		
		return annoMapper.selectByExample(null);
	}

}
