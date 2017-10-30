package com.hsu.netchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsu.netchat.bean.DevlpTeam;
import com.hsu.netchat.bean.Msg;
import com.hsu.netchat.service.TeamService;

@Controller
public class AboutUsController {
	@Autowired
	private TeamService teamService;
	
	@ResponseBody
	@RequestMapping("/aboutUs")
	public Msg aboutUs(){
		DevlpTeam devlpTeam = teamService.getDevlpTeamInfo();
		return Msg.success("获得团队信息成功！").add("teamInfo", devlpTeam);
	}
}
