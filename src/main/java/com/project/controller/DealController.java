package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.domain.DealHistoryDTO;
import com.project.domain.PostDTO;
import com.project.service.DealService;
import com.project.service.PostService;

@Controller
public class DealController {

	@Autowired
	private DealService dealService;
	
	@Autowired
	private PostService postService;

	// 나의 거래완료 내역(마이페이지)
	@GetMapping(value = "/mydealhistory")
	public String getEndDealList(Model model) {

		String userId = "kmg";

		List<DealHistoryDTO> dealList = dealService.getEndDealList(userId);
		System.out.println("dealList" + dealList);
		model.addAttribute("dealList", dealList);

		return "/mypage";
	}

	// 나의 거래중인 내역
	@GetMapping(value = "/dealing")
	public String getDealingList(Model model) {

		String userId = "kmg";

		List<DealHistoryDTO> dealList = dealService.getDealingList(userId);
		System.out.println(dealList);
		model.addAttribute("dealList", dealList);

		return "/index";
	}

	@GetMapping(value = "/sortMain")
	public String openPostList(@RequestParam(value = "sortOption", required = false) String sortOpt, Model model) {
		System.out.println("sortOption:" + sortOpt);
		List<PostDTO> postList = postService.getPostSortList(sortOpt);
		model.addAttribute("postList", postList);
		return "/main";
	}

}
