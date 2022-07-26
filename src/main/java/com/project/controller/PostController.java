package com.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.project.domain.*;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.constant.Method;
import com.project.service.CatService;
import com.project.service.CommentService;
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

	// 게시글 작성 폼으로
	@GetMapping(value = "/write.do")
	public String openPostWrite(@ModelAttribute("catnum") CatDTO catnum, @ModelAttribute("params") PostDTO params,@RequestParam(value = "pnum", required = false) Long pnum, Model model) {
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
			logger.info(""+catlist);
			model.addAttribute("post", post);
			logger.info(""+post);
		}

		logger.info("PostDTO-->" + params);
		return "/write";
	}

	// 게시글 등록, 수정
		@PostMapping(value = "/register.do")
		public String registerPost(final PostDTO params, MultipartFile file)throws Exception {
			logger.info("" + params);
			
			try { // 파일업로드
				if (!file.isEmpty()) {
					FileUtil fileUtil = new FileUtil();
					FileDTO fileDTO = fileUtil.fileUpload(file);
					System.out.println("저장된 filevo: " + fileDTO.toString());
					System.out.println("저장된 file이름: " + fileDTO.getSaveName());
				
					// 블로그 logo-name 설정
					params.setPhoto(fileDTO.getSaveName());
					
					// security
					Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					// String id= ( (UserDTO)session.getAttribute("userDTO") ).getUserid();
					String id = ((UserDTO) principal).getUserid();
					params.setLeaderid(id);
					
					TeamMemberDTO captain = new TeamMemberDTO();
					captain.setUserId(id);

					// registeredPnum : 만약 업데이트라면 1, 새로운 등록이면 pnum, 실패하면 0이 리턴됨.
					
					boolean isRegistered = postService.registerPost(params, captain);

					System.out.println(">>>>>>>>>isRegistered>" + isRegistered);

					if (isRegistered == false) { // TODO => 게시글등록에 실패하였다는 메시지를 전달
						System.out.println("<-----게시글 등록 실패----->");
					}
				} else {
					System.out.println("<-----파일이 존재하지 않습니다.----->");
					
				}
			} catch (DataAccessException e) { // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는메시지를 전달
				System.out.println(e.getMessage());
				System.out.println("<-----데이터베이스 처리 과정 문제 발생----->");
			} catch (Exception e) { // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
				System.out.println("<-----시스템에 문제 발생----->");
			}
			System.out.println("<--------------------------------메인-------------------------------->");
			logger.info("PostDTO-->" + params);
			return "redirect:/main.do";
		}
	
	//@RequestMapping(value = "/main.do")
	@GetMapping(value = "/main.do")
	public String openPostList(HttpServletRequest req, RedirectAttributes rtt,
							@RequestParam(value="keyword", required=false) String keyword,
							@RequestParam(value="category", required=false, defaultValue="0") String category,
							@RequestParam(value="sortoption", required=false) String sortopt, Model model) {
		
		System.out.println("keyword: " + keyword + " category: " + category + " sortoption: " + sortopt);
		
		if(keyword==null && category=="0" && sortopt ==null) {
			List<PostDTO> postList = postService.getPostList();
			model.addAttribute("postList", postList);
			
		}else {
			String optionName;
			List<PostDTO> postList = postService.getSearchPostList(keyword, category, sortopt);
			model.addAttribute("postList", postList);
			model.addAttribute("keyword", keyword);
			model.addAttribute("cateNum", category);
			model.addAttribute("sortoption", sortopt);
		}
		return "/main";
	}
	
	// 게시글 상세내용 detail
	@GetMapping(value = "/detail.do")
	public String openPostDetail(@ModelAttribute("params") PostDTO params, @RequestParam(value = "pnum", required = false) Long pnum, Model model) {
		System.out.println("현재 -->" + this.getClass().getName() + "<-- 수행중...");
		System.out.println("현재 pnum -->" + pnum);


		PostDTO postDTO = postService.getPostDetail(pnum); // 임의의 pnum
		
		System.out.println("postDTO>>"+postDTO);
		System.out.println("postDTO.getLeaderid()>>"+postDTO.getLeaderid());
		

		if (postDTO == null || "Y".equals(postDTO.getDeleteyn())) {
			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/main.do";
		}

		List<CommentDTO> commentList= commentService.getCommentList(pnum);
		List<UserDTO> teamMemberList = teamMemberService.getTeamMembertList(pnum);
		List<UserDTO> otherMemberList = teamMemberService.getOtherMembertList(pnum);

		model.addAttribute("postDTO", postDTO);

		model.addAttribute("commentList", commentList); // 댓글 리스트 보내주기 위함
		model.addAttribute("comment", new CommentDTO()); // 댓글에서 객체를 받아오기 위해서 사용

		model.addAttribute("otherMemberList", otherMemberList);
		model.addAttribute("otherMember", new UserDTO());
		
		
		model.addAttribute("teamMemberList", teamMemberList);
		model.addAttribute("teamMember", new UserDTO());
		
		//거래장소 : 방장 주소 기준
		String leaderId =  postDTO.getLeaderid();
		String leaderPlace ="";
		for(int i =0; i<teamMemberList.size(); i++) {
			System.out.println("teamMemberList.get(i)>>"+teamMemberList.get(i).getUserid());
			
			if( (teamMemberList.get(i).getUserid()).equals(leaderId) ) {
				
				//System.out.println("teamMemberList.get(i)>>"+teamMemberList.get(i).getUserid());
				System.out.println("leaderId>>"+leaderId);
				leaderPlace = teamMemberList.get(i).getAddress();
				System.out.println("leaderPlace>>"+leaderPlace);
				
			}else {
				System.out.println("if문 안됨");
				
			}
		}
		System.out.println("수정 후 leaderPlace>>model>"+leaderPlace);
		model.addAttribute("leaderPlace", leaderPlace);

		System.out.println("teamMemberList>>"+teamMemberList);//애매
		System.out.println(commentList);

		int countMember = teamMemberService.selectTeamMemberTotalCount(pnum);
		model.addAttribute("countMember", countMember);

		if (countMember >= postDTO.getMinpeople()){
			model.addAttribute("minpeople", true);
			System.out.println(countMember);
		}

		try{
			DataModel dm = new FileDataModel(new File("/Users/jihyeonjeong/KOSTA_Project/data/recommend")); // 데이터 모델 생성
			TanimotoCoefficientSimilarity sim = new TanimotoCoefficientSimilarity(dm); // 유사도 모델 선택
			GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim); // 추천기 선택: ItemBased
			for (LongPrimitiveIterator items = dm.getItemIDs(); items.hasNext();) { // 데이터 모델 내의 Item들의 iterator를 단계별 이동하여 추천 아이템 제공
				long itemId = items.nextLong(); // 현재 Item ID

				List<RecommendedItem> recommendations = recommender.mostSimilarItems(itemId, 5); // 현재 item ID와 가장 유사한 5개 아이템 추천
				List<PostDTO> recommendPostList = new ArrayList<PostDTO>(); // 추천 아이템 리스트를 받는 ArrayList 생성
				if(pnum == itemId){ // 게시글 번호랑 현재 Item ID가 같다면
					for (RecommendedItem recommendation : recommendations) {  // 유사한 아이템 출력  = 현재 아이템 ID | 추천된 아이템 ID | 유사도
						System.out.println(itemId + "," + recommendation.getItemID() + "," + recommendation.getValue());

						System.out.println("현재 pnum: "+pnum +" 유사한 아이템: "+recommendation.getItemID());

						recommendPostList = postService.recommendPostList(recommendation.getItemID());
						// PostDTO recommendPost = postService.getPostDetail(recommendation.getItemID());
					}
					model.addAttribute("recommendPostList", recommendPostList);
					 System.out.println("추천 리스트: "+recommendPostList);
					model.addAttribute("recommend", new PostDTO());
				}
			}
		} catch (IOException e) {
			System.out.println("there was an error.");
			e.printStackTrace();
		} catch (TasteException e) {
			System.out.println("there was an Taste Exception.");
			e.printStackTrace();
		}

		return "/detail";
	}
	
	@PostMapping(value = "/delete.do")
	public String deletePost(@RequestParam(value = "pnum", required = false) Long pnum, Model model) {
		System.out.println("/delete.do 접근 --->"+pnum);
		// 올바르지 않은 접근 시
		if (pnum == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/main.do", Method.GET, null, model);
		}

		try {
			System.out.println("try 접근. pnum = " + pnum);
			boolean isDeleted = postService.deletePost(pnum);
			System.out.println("deletePost 실행 후. isDeleted = "+isDeleted);
			
			// false면 이미 게시글이 삭제된 상태
			if (isDeleted == false) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/main.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/main.do", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/main.do", Method.GET, null, model);
		}
		logger.info("pnum"+pnum);
		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/main.do", Method.GET, null, model);
	}


}
