package com.hsu.netchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DicController {
	
	/**
	 * 转发到帮助页面
	 * @return
	 */
	@RequestMapping("/help")
	public String helpPage(){
		return "help";
	}
}
