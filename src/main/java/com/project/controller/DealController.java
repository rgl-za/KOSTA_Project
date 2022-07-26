package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.domain.DealHistoryDTO;
import com.project.domain.HeartDTO;
import com.project.domain.PostDTO;
import com.project.domain.UserDTO;
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
	public String getEndDealList(Model model, @AuthenticationPrincipal UserDTO userDTO) {

		String userId =  userDTO.getUserid();
		System.out.println("userId: " + userId);
		
		List<DealHistoryDTO> dealList = dealService.getEndDealList(userId);
		System.out.println("dealList" + dealList);
		model.addAttribute("dealList", dealList);
		model.addAttribute("userDTO", userDTO);

		return "/mypage";
	}

	// 나의 거래중인 내역
	@GetMapping(value = "/dealing")
	public String getDealingList(Model model, @AuthenticationPrincipal UserDTO userDTO) {

		//UserDTO user = (UserDTO) session.getAttribute("userDTO");
		String userId =  userDTO.getUserid();
		System.out.println("userId: " + userId);

		List<DealHistoryDTO> dealList = dealService.getDealingList(userId);
		System.out.println(dealList);
		model.addAttribute("dealList", dealList);

		return "/index";
	}

}
