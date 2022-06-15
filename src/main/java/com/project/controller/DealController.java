package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.domain.DealHistoryDTO;
import com.project.service.DealService;

@Controller
public class DealController {

	@Autowired
	private DealService dealService;
	
	
	// 나의 거래완료 내역
	@GetMapping(value = "/dealhistory")
	public String getEndDealList(Model model) {

		String userId = "kmg";

		List<DealHistoryDTO> dealList = dealService.getEndDealList(userId);
		System.out.println("dealList" + dealList);
		model.addAttribute("dealList", dealList);

		return "/breakdown";
	}
	
	// 나의 거래완료 내역
	@GetMapping(value = "/dealing")
	public String getDealingList(Model model) {

		String userId = "kmg";

		List<DealHistoryDTO> dealList = dealService.getDealingList(userId);
		System.out.println(dealList);
		model.addAttribute("dealList", dealList);

		return "/index";
	}

}
