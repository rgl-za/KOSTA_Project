package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.service.CatService;

@Controller
public class CatController {
	
	@Autowired
	private CatService catService;
	
	
}
