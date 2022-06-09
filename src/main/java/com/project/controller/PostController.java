package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.domain.PostDTO;
import com.project.service.PostService;


public class PostController {

	@Autowired
	private PostService postService;

	// 게시글 작성 폼으로
	@GetMapping(value = "/post/write.do")
	public String openPostWrite(@RequestParam(value = "pnum", required = false) Long pnum, Model model) {
	  if (pnum == null) {
	    model.addAttribute("post", new PostDTO());
	  } else {
	    PostDTO post = postService.getPostDetail(pnum);
	    if (post == null) {
	      return "redirect:/post/main.do";
	    }
	    model.addAttribute("post", post);
	  }

	  return "post/write";
	}
	
	// 게시글 등록, 수정
	@PostMapping(value = "/post/register.do")
	public String registerPost(final PostDTO params) {
	  try {
	    boolean isRegistered = postService.registerPost(params);
	    System.out.println("");
	    if (isRegistered == false) {
	      // TODO => 게시글 등록에 실패하였다는 메시지를 전달
	    }
	  } catch (DataAccessException e) {
	    // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
	  } catch (Exception e) {
	    // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
	  }
	  return "redirect:/post/main.do";
	}
	
	// 게시글을 올리고 main으로
	@GetMapping(value = "/post/main.do")
	public String openPostList(Model model) {
		List<PostDTO> postList = postService.getPostList();
		model.addAttribute("postList", postList);

		return "post/main";
	}
	
	// 게시글 상세내용 detail
	@GetMapping(value = "/post/detail.do")
	public String openPostDetail(@RequestParam(value = "pnum", required = false) Long pnum, Model model) {
		System.out.println("현재 -->" + this.getClass().getName() + "<-- 수행중..." );
		if (pnum == null) {
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/post/main.do";
		}

		PostDTO post = postService.getPostDetail(pnum);
		if (post == null || "Y".equals(post.getDelete_yn())) {
			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/post/main.do";
		}
		model.addAttribute("post", post);

		return "post/detail";
	}
}
