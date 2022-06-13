package com.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.domain.FileDTO;
import com.project.domain.PostDTO;
import com.project.service.PostService;
import com.project.util.FileUtil;

@Controller
public class PostController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PostService postService;

	// 게시글 작성 폼으로
	@GetMapping(value = "/write.do")
	public String openPostWrite(@ModelAttribute("params") PostDTO params, @RequestParam(value = "pnum", required = false) Long pnum, Model model) {
		if (pnum == null) {
			model.addAttribute("post", new PostDTO());
			System.out.println();
		} else {
			PostDTO post = postService.getPostDetail(pnum);
			if (post == null) {
				return "redirect:/main.do";
			}
			model.addAttribute("post", post);
		}
		System.out.println("logger");
		logger.info("write.do");
		return "/write";
	}

	// 게시글 등록, 수정
	@PostMapping(value = "/register.do" )
	public String registerPost(final PostDTO params, MultipartFile file) {
	System.out.println("file!" + file);
	System.out.println("register.do");
	System.out.println(params);
	System.out.println(params.getPhoto());
		try {
			// 파일업로드
			if(!file.isEmpty()) {
				
				FileUtil fileUtil = new FileUtil();
				FileDTO fileDTO = fileUtil.fileUpload(file);
				System.out.println("저장된 filevo: "+ fileDTO.toString());
				System.out.println("저장된 file이름: "+ fileDTO.getSaveName());
				
				//블로그 logo-name 설정
				params.setPhoto(fileDTO.getSaveName());
				
				
				boolean isRegistered = postService.registerPost(params);
				System.out.println(isRegistered);
				if (isRegistered == false) {
					// TODO => 게시글 등록에 실패하였다는 메시지를 전달
					System.out.println("등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!");
				}
			}else {
				boolean isRegistered = postService.registerPost(params);
				System.out.println(isRegistered);
				if (isRegistered == false) {
					// TODO => 게시글 등록에 실패하였다는 메시지를 전달
					System.out.println("등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!등록!실패!");
				}
			}
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
			System.out.println("처리과정실패!처리과정실패!처리과정실패!처리과정실패!처리과정실패!처리과정실패!처리과정실패!처리과정실패!처리과정실패!처리과정실패!처리과정실패!처리과정실패!처리과정실패!");
		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
			System.out.println("시스템문제!시스템문제!시스템문제!시스템문제!시스템문제!시스템문제!시스템문제!시스템문제!시스템문제!시스템문제!시스템문제!시스템문제!시스템문제!시스템문제!시스템문제!");
		}
		logger.info("write.do");
		return "redirect:/main.do";
	}

	// 게시글을 올리고 main으로
	@GetMapping(value = "/main.do")
	public String openPostList(Model model) {
		List<PostDTO> postList = postService.getPostList();
		model.addAttribute("postList", postList);
		logger.info("main.do");
		return "/main";
	}

	// 게시글 상세내용 detail
	@GetMapping(value = "/detail.do")
	public String openPostDetail(@RequestParam(value = "pnum", required = false) Long pnum, Model model) {
		System.out.println("현재 -->" + this.getClass().getName() + "<-- 수행중...");
		if (pnum == null) {
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
//			return "redirect:/main.do";
			return "/detail";
		}

		PostDTO post = postService.getPostDetail(pnum);
		if (post == null || "Y".equals(post.getDelete_yn())) {
			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/main.do";
		}
		model.addAttribute("post", post);

		logger.info("detail.do");
		return "/main";
	}
	
}
