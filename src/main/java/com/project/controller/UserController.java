package com.project.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
//@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    UserService userService;
	

    @GetMapping("/register")
    public String signUpForm(UserDTO userDTO) {
    	System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddd");
        return "/register";
    }
    
//    @ResponseBody
//    @PostMapping("/idCheck")
//	public int overlappedID(String userid)throws Exception {
//		int result = userService.overlappedID(userid);
//		
//		logger.info("*********************************" +result);
//		return result;
//	}
 
    @ResponseBody
    @PostMapping("/idCheck")
	public int overlappedID(@RequestParam("userid") String userid) throws Exception{
		int cnt = userService.overlappedID(userid);
		System.out.println(cnt + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		return cnt;
	}
    

    

    

    @PostMapping("/register")
    public String execSignUp(@Valid UserDTO userDTO, Errors errors, Model model, String username, String nickname)throws Exception{
    	
        
        if(errors.hasErrors()) {
        	
        	model.addAttribute("userDTO", userDTO);
        	model.addAttribute("username", username);
        	model.addAttribute("nickname", nickname);
        	
        	System.out.println("dkdkdk아아아ㅏ아아아ㅏ아아아아아ㅏ아아아ㅏ아아아ㅏ아아아아" + userDTO);
        	
        	Map<String, String> validatorResult = userService.validateHandling(errors);
        	 for (String key : validatorResult.keySet()) {
                 model.addAttribute(key, validatorResult.get(key));
        	}
         	
        	 return "/register";
        }
        
        userService.joinUser(userDTO);
        return "redirect:/login";

    }
    
    @GetMapping("/login")
    public String memberLogin () { 
    
    System.out.println("go to login page!");
    	return "/login";
    }

    
    @PostMapping(value= "/login")
    public String Login(HttpServletRequest req, RedirectAttributes rtt) {
	
    	return "redirect:/main.do";
    }
    
//    public String currentUsername(Principal principal) {
//    	return principal.getName();
//    }
    

    
    @GetMapping("/UpdateUser")
    public String UpdateUser() {
    	return "/UpdateUser";
    }
    
    @PostMapping("/UpdateUser")
    public String UpdateUser(UserDTO userDTO) throws Exception{
    	
    	userService.UserUpdate(userDTO);
    	
    	return "redirect:/main.do";
    }
    

    
//    @GetMapping("/logout")
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//    	System.out.println("로그아웃^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^66");
//    	new SecurityContextLogoutHandler().logout(request, response,SecurityContextHolder.getContext().getAuthentication());
//    	return "redirect:/UpdateUser";
//    }
//    

}