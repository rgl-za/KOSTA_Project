package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class BoardController{
//
//	@Autowired
//	private BoardService boardService;


	@GetMapping(value = "/")
	public String openBoardList(Model model) {
<<<<<<< HEAD
=======
		return "/index";
		//return "/fragments/header";
		//return "/layouts/index";
		//return "/layouts/login";
	}
>>>>>>> c20091d8f4bbaedfef8a8b5f7705dc7ddc4320b2

//		return "/index";
//		return "/fragments/header";
//		return "/layouts/index";
		return "/layouts/login";

	}
}