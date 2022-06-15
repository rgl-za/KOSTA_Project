package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.service.PostService;

@Controller
public class PostFileController {
	
	@Autowired
	private PostService postService;
}
