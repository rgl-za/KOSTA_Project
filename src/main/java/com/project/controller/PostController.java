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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.constant.Method;
import com.project.domain.CatDTO;
import com.project.domain.CommentDTO;
import com.project.domain.FileDTO;
import com.project.domain.PostDTO;
import com.project.domain.TeamMemberDTO;
import com.project.service.CatService;
import com.project.service.CommentService;
import com.project.service.FileService;
import com.project.service.PostService;
import com.project.service.TeamMemberService;
import com.project.util.FileUtil;
import com.project.util.UiUtils;

@Controller
public class PostController extends UiUtils {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private TeamMemberService teamMemberService;

	@Autowired
	private CatService catService;

	@Autowired
	private FileService fileService;

	// 게시글 작성 폼으로
	@GetMapping(value = "/write.do")
	public String openPostWrite(@ModelAttribute("catnum") CatDTO catnum, @ModelAttribute("params") PostDTO params,
			@RequestParam(value = "pnum", required = false) Long pnum, Model model) {
		logger.info("PostDTO" + params);
		if (pnum == null) { // pnum이 null일 경우 빈 객체를 보여준다
			model.addAttribute("post", new PostDTO());
			List<CatDTO> catlist = catService.selectCatList(catnum);
			model.addAttribute("catlist", catlist);
		} else { // pnum에서 받아온 경우
			List<CatDTO> catlist = catService.selectCatList(catnum);
			PostDTO post = postService.getPostDetail(pnum);

			if (post == null) {
				return "redirect:/main.do";
			}
			model.addAttribute("catlist", catlist);
			logger.info("" + catlist);
			model.addAttribute("post", post);
			logger.info("" + post);
		}

		logger.info("PostDTO-->" + params);
		return "/write";
	}

	// 게시글 작성 폼으로
//	@GetMapping(value = "/write.do")
//	public String openPostWrite(@ModelAttribute("fnum") FileDTO fnum, @ModelAttribute("catnum") CatDTO catnum, @ModelAttribute("params") PostDTO params,@RequestParam(value = "pnum", required = false) Long pnum, Model model) {
//		logger.info("PostDTO" + params);
//		if (pnum == null) { // pnum이 null일 경우 빈 객체를 보여준다
//			 model.addAttribute("post", new PostDTO());
//			 List<CatDTO> catlist = catService.selectCatList(catnum);
//			 model.addAttribute("catlist", catlist);
//		} else { // pnum에서 받아온 경우
//			// 카테고리 가져온다
//			List<CatDTO> catlist = catService.selectCatList(catnum);
//			// 작성했던 내용들을 가져온다.
//			PostDTO post = postService.getPostDetail(pnum);
//			
//			List<FileDTO> filelist = fileService.selectFileList(fnum);
//			model.addAttribute("filelist", filelist);
//			
//			if (post == null) {
//				return "redirect:/main.do";
//			}
//			model.addAttribute("filelist", filelist);
//			logger.info(""+filelist);
//			model.addAttribute("catlist", catlist);
//			logger.info(""+catlist);
//			model.addAttribute("post", post);
//			logger.info(""+post);
//		}
//
//		logger.info("PostDTO-->" + params);
//		return "/write";
//	}

//	
	@PostMapping(value = "/register.do")
	public String registerPost(final PostDTO params, MultipartFile file) {
		logger.info("register.do" + params);
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
				if (isRegistered == true) { // TODO => 게시글등록에 실패하였다는 메시지를 전달
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
		logger.info("PostDTO-->" + params);
		return "redirect:/main.do";
	}
//	

	// 게시글 등록, 수정
//	@PostMapping(value = "/register.do")
//	public String registerPost(final PostDTO params, Model model, MultipartFile file ) {
//		logger.info("register.do" + params);
//		try {
//			if (!file.isEmpty()) {
//			FileUtil fileUtil = new FileUtil();
//			FileDTO fileDTO = fileUtil.fileUpload(file);
//			System.out.println("저장된 filevo: " + fileDTO.toString());
//			System.out.println("저장된 file이름: " + fileDTO.getSaveName());
//			
//			// 블로그 logo-name 설정 
//			params.setPhoto(fileDTO.getSaveName());
//			
//			boolean isRegistered = postService.registerPost(params);
//			System.out.println(isRegistered);
//			if (isRegistered == false) { // TODO => 게시글등록에 실패하였다는 메시지를 전달
//				System.out.println("<-----게시글 등록 실패----->");
//			}
//		}
//			boolean isRegistered = postService.registerPost(params);
//			if (isRegistered == false) {
//				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/main.do", Method.GET, null, model);
//			}
//		} catch (DataAccessException e) {
//			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/main.do", Method.GET, null, model);
//
//		} catch (Exception e) {
//			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/main.do", Method.GET, null, model);
//		}
//
//		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/main.do", Method.GET, null, model);
//	}

	@GetMapping(value = "/store.do")
	public String openPostList(@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "category", required = false) String category, Model model) {
		System.out.println("keyword: " + keyword + " category: " + category);

		if (keyword != null || category != null) {
			List<PostDTO> postList = postService.getSearchPostList(keyword, category);
			model.addAttribute("postList", postList);
			model.addAttribute("cateNum", category);
		} else {
			List<PostDTO> postList = postService.getPostList();
			model.addAttribute("postList", postList);
		}
		return "/main";
	}
//	@GetMapping(value = "/main.do")
//	public String openPostList(@RequestParam(value = "keyword", required = false) String keyword,
//			@RequestParam(value = "category", required = false) String category, Model model) {
//		System.out.println("keyword: " + keyword + " category: " + category);
//		
//		if (keyword != null || category != null) {
//			List<PostDTO> postList = postService.getSearchPostList(keyword, category);
//			model.addAttribute("postList", postList);
//			model.addAttribute("cateNum", category);
//		} else {
//			List<PostDTO> postList = postService.getPostList();
//			model.addAttribute("postList", postList);
//		}
//		return "/main";
//	}

	// 게시글 상세내용 detail
	@GetMapping(value = "/detail.do")
	public String openPostDetail(@ModelAttribute("params") PostDTO params,
			@RequestParam(value = "pnum", required = false) Long pnum, Model model) {
		System.out.println("현재 -->" + this.getClass().getName() + "<-- 수행중...");
		System.out.println("현재 pnum -->" + pnum);
//		long pnumex = 1;

		PostDTO postDTO = postService.getPostDetail(pnum); // 임의의 pnum

		if (postDTO == null || "Y".equals(postDTO.getDeleteyn())) {
			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/main.do";
		}

		List<CommentDTO> commentList = commentService.getCommentList(pnum);
		List<TeamMemberDTO> teamMemberList = teamMemberService.getTeamMembertList(pnum);

		model.addAttribute("postDTO", postDTO);

		model.addAttribute("commentList", commentList); // 댓글 리스트 보내주기 위함
		model.addAttribute("comment", new CommentDTO()); // 댓글에서 객체를 받아오기 위해서 사용

		model.addAttribute("teamMemberList", teamMemberList);
		model.addAttribute("teamMember", new TeamMemberDTO());

		// PostDTO post = postService.getPostDetail(pnum);

		System.out.println(commentList);
//		if (post == null || "Y".equals(post.getDelete_yn())) {
//			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
//			return "redirect:/main.do";
//		}
		// model.addAttribute("post", post);
		// logger.info("detail.do");

		int countMember = teamMemberService.selectTeamMemberTotalCount(pnum);
		model.addAttribute("countMember", countMember + 1);

		if (countMember >= postDTO.getMinpeople()) {
			model.addAttribute("minpeople", true);
			System.out.println(countMember);
		}

		return "/detail";
	}

	@PostMapping(value = "/delete.do")
	public String deletePost(@RequestParam(value = "pnum", required = false) Long pnum, Model model) {
		System.out.println("/delete.do 접근 --->" + pnum);
		// 올바르지 않은 접근 시
		if (pnum == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/main.do", Method.GET, null, model);
		}

		try {
			System.out.println("try 접근. pnum = " + pnum);
			boolean isDeleted = postService.deletePost(pnum);
			System.out.println("deletePost 실행 후. isDeleted = " + isDeleted);

			// false면 이미 게시글이 삭제된 상태
			if (isDeleted == false) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/main.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/main.do", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/main.do", Method.GET, null, model);
		}
		logger.info("pnum" + pnum);
		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/main.do", Method.GET, null, model);
	}

	@GetMapping(value = "/sortMain/{sortOption}")
	public String MainPostList(@ModelAttribute("params") PostDTO params, @PathVariable("sortOption") String sortOption,
			Model model) {
		System.out.println("sortOption:" + sortOption);

		if (sortOption.equals("latest")) {
			System.out.println("sortOption:" + sortOption);
			List<PostDTO> postList = postService.getPostSortList(sortOption);
			model.addAttribute("postList", postList);

		} else if (sortOption.equals("popular")) {

			List<PostDTO> postList = postService.getPostSortList(sortOption);
			model.addAttribute("postList", postList);

		} else {

			List<PostDTO> postList = postService.getPostSortList(sortOption);
			model.addAttribute("postList", postList);

		}

		return "redirect:/main";
	}
	/*
	 * @GetMapping("/maintest") public String openMaintest() { return "/maintest"; }
	 */
	
	@GetMapping("/store")
	public String openMaintest1() {
		return "/store";
	}

}
