package com.project.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.domain.UserDTO;
import com.project.service.UserService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller

@RequiredArgsConstructor
@AllArgsConstructor
//@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    UserService userService;

    @GetMapping("/register")
    public String signUpForm() {
    	System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddd");
        return "/register";
    }
    
    @ResponseBody
    @PostMapping("/idCheck")
	public int overlappedID(String userid)throws Exception {
		int result = userService.overlappedID(userid);
		
		logger.info("*********************************" +result);
		return result;
	}
    
//    @ResponseBody
//    @PostMapping("/idCheck")
//	public int overlappedID(@RequestParam (value="userid" ,required=false) String userid)throws Exception {
//		int result = userService.overlappedID(userid);
//		
//		logger.info("*********************************" +result);
//		return result;
//	}

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
    
    System.out.println("go to login page!");
    	return "/login";
    }

    
    @PostMapping(value= "/login")
    public String Login(HttpServletRequest req, RedirectAttributes rtt) {
    	
//    	System.out.println("컨트롤러 메서드 진입@@@@@@@@@@@@@@@@");
//    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//    	UserDTO userDTO = (UserDTO) auth.getPrincipal();
//    	
//    	HttpSession session = req.getSession();
//    	
//    	System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%userid :" + userDTO.getUserid());
//    	System.out.println("#################authorities : "+userDTO.getAuthorities());
//    	
//    	session.setAttribute("userid", userDTO.getUserid());
//    	session.setAttribute("auth", userDTO.getAuthorities());
//    	
//    	System.out.println("session.userid" + session.getAttribute("userid"));
//    	System.out.println("session.userid" + session.getAttribute("auth"));
    	
    	return "redirect:/main.do";
    }
    
//    public String currentUsername(Principal principal) {
//    	return principal.getName();
//    }
    
    
//    @PostMapping("/login")
//    public String memberLogin(String userid, HttpSession session) throws Exception{
//    	
//    	userid = userService.memberLogin(userid);
//    	logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//    	if(userid != null) {
//    		session.setAttribute("userDTO", userid);
//    		
//    		logger.info("성공ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ" + session);
//    		return "redirect:/main.do";
//    	}else {
//    		
//    		logger.info("실패ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
//    	return "redirect:/user/login";
//    }
//    }
    
    @GetMapping("/UpdateUser")
    public String UpdateUser() {
    	return "/UpdateUser";
    }
    
    @PostMapping("/UpdateUser")
    public String UpdateUser(UserDTO userDTO, HttpSession session) throws Exception{
    	
    	userService.UserUpdate(userDTO);
    	logger.info("$$$$$$$$$$$$$$$$$$$수정$$$$$$$$$$$$$$$$");
    	
    	return "redirect:/mypage";
    }
    
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("로그아웃^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^66");
    	new SecurityContextLogoutHandler().logout(request, response,SecurityContextHolder.getContext().getAuthentication());
    	return "redirect:/main.do";
    }
    

}