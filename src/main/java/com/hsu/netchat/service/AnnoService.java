package com.hsu.netchat.service;

import java.util.List;

import com.hsu.netchat.bean.Anno;

public interface AnnoService {
	
	/**
	 * 获取数据库中的公告信息
	 * @return
	 */
	List<Anno> getAnnoMsg();

}
