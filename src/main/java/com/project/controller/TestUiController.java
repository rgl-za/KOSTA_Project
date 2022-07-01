package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TestUiController {
	
	// UI soft 시작
	
	@GetMapping(value = "/uisoft.do")
	  public String opendashboard() {
	    

	    return "/Uisoft/dashboard";
	  }
	
	@GetMapping(value = "/Uisoft/billing.do")
	  public String openbilling() {
	    

	    return "Uisoft/billing";
	  }
	
	@GetMapping(value = "/Uisoft/profile.do")
	public String openprofile() {
		
		
		return "Uisoft/profile";
	}
	
	@GetMapping(value = "/Uisoft/rtl.do")
	public String openrtl() {
		
		
		return "Uisoft/rtl";
	}
	
	@GetMapping(value = "/Uisoft/signin.do")
	  public String opensignin() {
	    

	    return "/Uisoft/signin";
	  }

	@GetMapping(value = "/Uisoft/signup.do")
	public String opensignup() {
		
		
		return "/Uisoft/signup";
	}
	
	@GetMapping(value = "/Uisoft/tables.do")
	public String opentables() {
		
		
		return "/Uisoft/tables";
	}

	@GetMapping(value = "/Uisoft/virtualreality.do")
	public String openvirtualreality() {
		
		
		return "/Uisoft/virtualreality";
	}
	
	// UI soft 끝
	
	
	
	
	
	
	
	@GetMapping(value = "/uidashmin.do")
	  public String openDashminUiList() {
	    

	    return "/Uidashmin/index";
	  }
	
}
