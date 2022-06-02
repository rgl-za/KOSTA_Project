package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController{

	@GetMapping(value = "/")
	public String openBoardList(Model model) {
<<<<<<< HEAD
		//return "/main";
		//return "/pick";
		return "/detail";
		//return "/fragments/header";
		//return "/layouts/index";
		//return "/layouts/login";
=======

		return "/index";
//		return "/index2";
//		return "/index3";
//		return "/test";
//		return "/fragments/header";
//		return "/layouts/login";
>>>>>>> 175b2db077752e5502bc300f27fbcbbcb440c32f

	}
}