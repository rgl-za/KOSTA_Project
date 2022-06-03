package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController{

	@GetMapping(value = "/")
	public String openBoardList(Model model) {
		//return "/main";
		return "/detail";
		//return "/index";
//		return "/index2";
//		return "/index3";
//		return "/test";
//		return "/fragments/header";
//		return "/layouts/login";


	}
}