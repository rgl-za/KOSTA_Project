package com.project.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.domain.UserDTO;
import com.project.service.UserService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    UserService userService;

    @GetMapping("/register")
    public String signUpForm() {
        return "/register";
    }
    
    @ResponseBody
    @PostMapping("/idCheck")
	public int overlappedID(@RequestParam (value="userid" ,required=false) String userid) {
		int result = userService.overlappedID(userid);
		
		logger.info("*********************************" +result);
		return result;
	}

    @PostMapping("/register")
    public String execSignUp(@Valid UserDTO userDTO, Errors errors, Model model){
    	model.addAttribute("userDTO", userDTO);
        
        if(errors.hasErrors()) {
        	Map<String, String> validatorResult = userService.validateHandling(errors);
        	 for (String key : validatorResult.keySet()) {
                 model.addAttribute(key, validatorResult.get(key));
        	}
        	 return "/register";
        }
        
        userService.joinUser(userDTO);
        return "redirect:/user/login";

 }
    
    @GetMapping("/login")
    public String memberLogin () {
    	return "/login";
    }
    
    
    @PostMapping("/login")
    public String memberLogin(UserDTO userDTO, HttpSession session) throws Exception{
    	
    	userDTO = userService.memberLogin(userDTO);
    	logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    	if(userDTO != null) {
    		session.setAttribute("userDTO", userDTO);
    		logger.info("성공ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ" + session);
    		return "redirect:/main.do";
    	}else {
    		
    		logger.info("실패ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
    	return "redirect:/user/login";
    }
    

    
    }
    
    @GetMapping("/UpdateUser")
    public String UpdateUser() {
    	return "/UpdateUser";
    }
    
    @PostMapping("/UpdateUser")
    public String UpdateUser(UserDTO userDTO, HttpSession session) throws Exception{
    	
    	userService.UserUpdate(userDTO);
    	
    	session.invalidate();
    	
    	return "redirect:/main";
    }
    
    

}