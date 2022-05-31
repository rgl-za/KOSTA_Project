package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class BoardController{


	@GetMapping(value = "/")
	public String openBoardList(Model model) {
		System.out.println("ㅅㅂ");
		return "/layouts/login";
//		return "/layouts/index";
//		return "/layouts/register";
		
	}

	
	

}