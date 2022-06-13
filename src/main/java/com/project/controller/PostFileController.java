package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.service.PostService;
import org.springframework.stereotype.Controller;

@Controller
public class PostFileController {
	
	@Autowired
	private PostService postService;
}
