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

import com.project.domain.CommentDTO;
import com.project.domain.FileDTO;
import com.project.domain.PostDTO;
import com.project.domain.TeamMemberDTO;

import com.project.service.CommentService;
import com.project.service.PostService;
import com.project.util.FileUtil;
import com.project.service.TeamMemberService;

@Controller
public class PostController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private TeamMemberService teamMemberService;

	// 게시글 작성 폼으로
	@GetMapping(value = "/write.do")
	public String openPostWrite(@ModelAttribute("params") PostDTO params,@RequestParam(value = "pnum", required = false) Long pnum, Model model) {
		logger.info("PostDTO" + params);
		if (pnum == null) { // pnum이 null일 경우 빈 객체를 보여준다
			model.addAttribute("post", new PostDTO());
		} else { // pnum에서 받아온 경우
			PostDTO post = postService.getPostDetail(pnum);
			if (post == null) {
				return "redirect:/main.do";
			}
			model.addAttribute("post", post);
		}

		/* logger.info("PostDTO" + params); */
		return "/write";
	}

	// 게시글 등록, 수정
	@PostMapping(value = "/register.do")
	public String registerPost(final PostDTO params, MultipartFile file) {
		logger.info("" + params);
		try { // 파일업로드
			if (!file.isEmpty()) {
				FileUtil fileUtil = new FileUtil();
				FileDTO fileDTO = fileUtil.fileUpload(file);
				System.out.println("저장된 filevo: " + fileDTO.toString());
				System.out.println("저장된 file이름: " + fileDTO.getSaveName());

				// 블로그 logo-name 설정 
				params.setPhoto(fileDTO.getSaveName());

				boolean isRegistered = postService.registerPost(params);
				System.out.println(isRegistered);
				if (isRegistered == false) { // TODO => 게시글등록에 실패하였다는 메시지를 전달
					System.out.println("<-----게시글 등록 실패----->");
				}
			} else {
				boolean isRegistered = postService.registerPost(params);
				System.out.println(isRegistered);
				if (isRegistered == false) { // TODO => 게시글등록에 실패하였다는 메시지를 전달 
					System.out.println("<-----게시글 등록실패----->");
				}
			}
		} catch (DataAccessException e) { // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는메시지를 전달
			System.out.println("<-----데이터베이스 처리 과정 문제 발생----->");
		} catch (Exception e) { // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
			System.out.println("<-----시스템에 문제 발생----->");
		}
		System.out.println("<--------------------------------메인-------------------------------->");
		logger.info("PostDTO" + params);
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
//		long pnumex = 1;

		PostDTO postDTO = postService.getPostDetail(pnum); // 임의의 pnum
		List<CommentDTO> commentList= commentService.getCommentList(pnum);
		List<TeamMemberDTO> teamMemberList = teamMemberService.getTeamMembertList(pnum);


		model.addAttribute("postDTO", postDTO);

		model.addAttribute("commentList", commentList); // 댓글 리스트 보내주기 위함
		model.addAttribute("comment", new CommentDTO()); // 댓글에서 객체를 받아오기 위해서 사용


		model.addAttribute("teamMemberList", teamMemberList);
		model.addAttribute("teamMember", new TeamMemberDTO());

		//PostDTO post = postService.getPostDetail(pnum);

		System.out.println(commentList);
//		if (post == null || "Y".equals(post.getDelete_yn())) {
//			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
//			return "redirect:/main.do";
//		}
		//model.addAttribute("post", post);
		//logger.info("detail.do");

		return "/detail";
	}

}
