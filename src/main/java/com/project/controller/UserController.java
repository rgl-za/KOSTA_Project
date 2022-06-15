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
	
	@GetMapping("/test")
	public String testForm() {
		return "Test";
	}
	
	@PostMapping("/test")
	public String test(UserDTO userDTO) {
		userService.joinUser(userDTO);
		return "redirect:/Test";
	}
	

}
