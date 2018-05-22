package com.hsu.netchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsu.netchat.bean.DevlpTeam;
import com.hsu.netchat.dao.DevlpTeamMapper;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private DevlpTeamMapper devlpTeamMapper;
	
	@Override
	public DevlpTeam getDevlpTeamInfo() {
		DevlpTeam devlpTeam = (DevlpTeam) devlpTeamMapper.selectByExample(null).get(0);
		return devlpTeam;
	}

}
