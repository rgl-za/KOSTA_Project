package com.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.PostDTO;
import com.project.domain.DealHistoryDTO;
import com.project.service.DealService;
import com.project.service.PostService;

@Controller
public class TestController {
	
	@Autowired
	private DealService dealService;

	// 나의 거래완료 내역
	@GetMapping(value = "/거래내역")
	  public String getEndDealList(Model model) {
	
	  String userId = "kmg";
	  
	  List<DealHistoryDTO> dealList = dealService.getEndDealList(userId);
	  System.out.println(dealList);
	  model.addAttribute("dealList", dealList);
	
	  return "/breakdown";
	 }
	
	// 나의 거래중인 내역
	@GetMapping(value = "/거래목록")
	  public String getDealingList(Model model) {
	
	  String userId = "kmg";
	  
	  List<DealHistoryDTO> dealList = dealService.getDealingList(userId);
	  model.addAttribute("dealList", dealList);
	
	  return "/index";
	 }
	
	


}