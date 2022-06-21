package com.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.domain.UserDTO;
import com.project.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    UserService userService;

    /**
     * 회원가입 폼
     * @return
     */
    @GetMapping("/register")
    public String signUpForm() {
        return "register";
    }

    /**
     * 회원가입 진행
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String signUp(UserDTO userDTO) {
    	logger.info("___________+++++++++++++++++++++++++++++++++++" + userDTO);
        userService.joinUser(userDTO);
        return "redirect:/register"; //로그인 구현 예정
    }


}
