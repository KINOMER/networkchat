package com.hsu.netchat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hsu.netchat.bean.Anno;
import com.hsu.netchat.service.AnnoService;


@Controller
public class AnnoController {
	
	@Autowired
	private AnnoService annoService;
	
	@RequestMapping("/getAnnoMsg")
	public void getAnno(){
		List<Anno> annoMsg = annoService.getAnnoMsg();
		System.out.println(annoMsg.get(0));
	}
}
