package com.project.controller;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.constant.Method;
import com.project.domain.PostDTO;

@Controller
public class BoardController {
//
//	@Autowired
//	private BoardService boardService;

	@GetMapping(value = "/")
	public String openBoardList(Model model) {
		return "/comingsoon";
	}

	@GetMapping(value = "/거래상세")
	public String openBoardListDetail(Model model) {
		return "detail";
	}

	@GetMapping(value = "/거래내역")
	public String openBoardListBreakdown(Model model) {
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
	public String openBoardListIndex(Model model) {
		
		return "/index";
	}

	/*
	 * @PostMapping(value = "/project/register.do") public String
	 * registerBoard(@ModelAttribute("params") final PostDTO params, Model model) {
	 * Map<String, Object> pagingParams = getPagingParams(params); try { boolean
	 * isRegistered = postService.registerBoard(params); if (isRegistered == false)
	 * { return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list.do",
	 * Method.GET, pagingParams, model); } } catch (DataAccessException e) { return
	 * showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/project/list.do",
	 * Method.GET, pagingParams, model);
	 * 
	 * } catch (Exception e) { return showMessageWithRedirect("시스템에 문제가 발생하였습니다.",
	 * "/project/list.do", Method.GET, pagingParams, model); }
	 * 
	 * return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/project/list.do",
	 * Method.GET, pagingParams, model); }
	 */

}