package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController{

	@GetMapping(value = "/")
	public String openBoardList(Model model) {

<<<<<<< HEAD


		return "/detail";
=======
		return "/detail";
		//return "/index";
>>>>>>> 1c413e00f43f6fb896a3e13b1e52ecb7ccc103dd
//		return "/index2";
//		return "/index3";
//		return "/test";
//		return "/fragments/header";
//		return "/layouts/login";



	}
}