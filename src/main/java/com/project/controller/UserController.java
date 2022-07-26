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
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
//    @ResponseBody
//    @PostMapping("/idCheck")
//	public int overlappedID(String userid)throws Exception {
//		int result = userService.overlappedID(userid);
//		
//		logger.info("*********************************" +result);
//		return result;
//	}
 
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/idCheck") public boolean overlappedID(@RequestParam("userid")
	 * String userid){ System.out.println("start!!");
	 * System.out.println("userid 들어왔니?: " + userid); int result =
	 * userService.overlappedID(userid); System.out.println("result 확인: " + result);
	 * if(result==1) { return false; }else { return true;
	 * 
	 * } }
	 */
    @ResponseBody
    @PostMapping("/idCheck")
    public String idCheck( @RequestParam(value="checkId", required=false) String userid) {
    	System.out.println("들어는오나?");
		/*
		 * if(userid != null) { int result = userService.overlappedID(userid);
		 * System.out.println("result 확인: " + result); return "false"; }else { int
		 * result = userService.overlappedID(userid); System.out.println("result 확인: " +
		 * result); return "true"; }
		 */
    	 
    	 int result = userService.overlappedID(userid);
         System.out.println("result 확인: " + result);
         String answer = String.valueOf(result);
         return answer;
    }
    
    @ResponseBody
    @PostMapping("/register")
    public String execSignUp(@Valid UserDTO userDTO, Errors errors, Model model, @RequestParam(value="checkId", required=false) String userid){
    	model.addAttribute("userDTO", userDTO);
    	
        System.out.println("설마 여기?" + userid);
        
        
        if(userid == null) {
        	//회원가입
        	if(errors.hasErrors()) {
            	Map<String, String> validatorResult = userService.validateHandling(errors);
            	 for (String key : validatorResult.keySet()) {
                     model.addAttribute(key, validatorResult.get(key));
            	}
            	return "/register";
            }else {
            	userService.joinUser(userDTO);
                return "redirect:/login";
            }
        	
         }else {
        	 //id 중복체크
        	 int result = userService.overlappedID(userid);
             System.out.println("result 확인: " + result);
             String answer = String.valueOf(result);
             return answer;

         }

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
    	logger.info("$$$$$$$$$$$$$$$$$$$수정$$$$$$$$$$$$$$$$");
    	
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