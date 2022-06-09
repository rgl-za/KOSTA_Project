package com.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.board.domain.BoardDTO;

@Controller
public class BoardController{
//
//	@Autowired
//	private BoardService boardService;

	@GetMapping(value = "/")
	public String openBoardList(Model model) {

		return "/main";
		//return "/templates/layouts/index";	
	}

	
	@GetMapping(value = "/거래상세")
	public String openBoardListDetail(Model model) {
		int pnum = 12345;
		List<BoardDTO> boardList = boardService.getBoardList(pnum);//임의의 pnum
		model.addAttribute("boardList", boardList);
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
	@GetMapping(value = "/거래목록")
	public String openBoardListDeal(Model model) {
		return "/index";
	}
}