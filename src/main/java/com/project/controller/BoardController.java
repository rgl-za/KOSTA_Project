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
		return "/comingsoon";
<<<<<<< HEAD
=======

>>>>>>> 35a73987c23ed84f700999550675ad1467b89170
	}
	
	@GetMapping(value = "/거래상세")
	public String openBoardListDetail(Model model) {
		return "detail";
	}
	
	@GetMapping(value = "/거래내역")
	public String openBoardListIndex(Model model) {
		return "/breakdown";
	}
	
	@GetMapping(value = "/메인")
	public String openBoardListMain(Model model) {
		return "/main";
	}
	
	@GetMapping(value = "/찜")
	public String openBoardListPick(Model model) {
		return "/pick";
	}
	
	@GetMapping(value = "/가입")
	public String openBoardListRegister(Model model) {
		return "/register";
	}
	
	@GetMapping(value = "/쓰기")
	public String openBoardListWrite(Model model) {
		return "/write";
	}
}