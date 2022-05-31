package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class BoardController{


	@GetMapping(value = "/")
	public String openBoardList(Model model) {
<<<<<<< HEAD
		System.out.println("ㅅㅂ");
		return "/layouts/login";
//		return "/layouts/index";
//		return "/layouts/register";
		
=======
		return "/index";
		//return "/fragments/header";
		//return "/layouts/index";
		//return "/layouts/login";
>>>>>>> c2d57cc409913eef0638f9f9176ff8eda2abd6e3
	}

	
	

}