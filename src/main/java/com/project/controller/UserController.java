package com.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.domain.UserDTO;
import com.project.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	

	


	@PostMapping("test")
	public String joinUser(UserDTO userDTO) {
		
		userService.insertUser(userDTO);
		System.out.println("확인용 ");

		return "redirect:/Test";
	}
	
	@PostMapping("ho")
	public String write() {
		return "hehe";
	}
}
