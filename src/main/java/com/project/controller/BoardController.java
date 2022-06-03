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
//		return "/detail";
//		return "/main";
		return "/pick";
		//return "/fragments/header";
		//return "/layouts/index";
		//return "/layouts/login";

	}
	
	@GetMapping(value = "/거래상세")
	public String openBoardListDetail(Model model) {
		return "detail";
	}
	
	@GetMapping(value = "/거래내역")
	public String openBoardListIndex(Model model) {
		return "/index";
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