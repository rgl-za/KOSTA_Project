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
import com.project.domain.CertificateDTO;
import com.project.domain.FileDTO;
import com.project.service.CertificateService;
import com.project.service.PostService;
import com.project.util.FileUtil;
import com.project.util.UiUtils;

@Controller
public class CertificateController extends UiUtils {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	private PostService postService;

	@GetMapping(value = "/cerwrite.do")
	public String openCerWrite(@ModelAttribute("params") CertificateDTO params,
			@RequestParam(value = "cernum", required = false) Long cernum, Model model) throws Exception {
		if (cernum == null) {
			model.addAttribute("cer", new CertificateDTO());

		} else {
			CertificateDTO cer = certificateService.getCerDetail(cernum);

			if (cer == null) {
				return "redirect:/cerwrite";
			}

			model.addAttribute("cer", cer);
			logger.info("" + cer);
		}
		// list로
		return "/cerwrite";
	}

	@PostMapping(value = "/cerregister.do")
	public String registerPost(final CertificateDTO params, MultipartFile file, Model model) {
		logger.info("" + params);
		try { // 파일업로드
			if (!file.isEmpty()) {
				FileUtil fileUtil = new FileUtil();
				FileDTO fileDTO = fileUtil.fileUpload(file);
				System.out.println("저장된 filevo: " + fileDTO.toString());
				System.out.println("저장된 file이름: " + fileDTO.getSaveName());

				// 블로그 logo-name 설정
				params.setCfile(fileDTO.getSaveName());

				boolean isRegistered = certificateService.registerCer(params);
				boolean pushFinaldate = postService.pushFinaldate(params);
				
				System.out.println(isRegistered);
				
				if (isRegistered == false) { // TODO => 게시글등록에 실패하였다는 메시지를 전달
					System.out.println("<-----게시글 등록 실패----->");
				}
			} else {
				System.out.println("<-----파일이 존재하지 않습니다.----->");

			}
		} catch (DataAccessException e) { // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는메시지를 전달
			System.out.println("<-----데이터베이스 처리 과정 문제 발생----->");
			return showMessageWithRedirect("데이터 처리에 실패했습니다. 빈칸없이 입력해 주세요.", "/cerwrite.do", Method.GET, null, model);
		} catch (Exception e) { // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
			System.out.println("<-----시스템에 문제 발생----->");
			return showMessageWithRedirect("시스템에 문제가 발생했습니다.", "/cermain.do", Method.GET, null, model);
		}
		logger.info("PostDTO-->" + params);

		return "redirect:/cermain.do";
	}

	// 관리자 
	@GetMapping(value = "/cermain.do")
	public String openPostList(CertificateDTO params, Model model) throws Exception {

		List<CertificateDTO> cer = certificateService.getCerList();
		model.addAttribute("cer", cer);

		return "/cermain";
//		return "/main";
	}

	@GetMapping(value = "/cerdetail.do")
	public String openPostDetail(@ModelAttribute("params") CertificateDTO params,
			@RequestParam(value = "cernum", required = false) Long cernum, Model model) throws Exception {
		System.out.println("현재 -->" + this.getClass().getName() + "<-- 수행중...");
		System.out.println("현재 pnum -->" + cernum);
//		long pnumex = 1;

		CertificateDTO cerDTO = certificateService.getCerDetail(cernum); // 임의의 pnum

		if (cerDTO == null || "Y".equals(cerDTO.getDeleteyn())) {
			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return showMessageWithRedirect("없는 게시글이거나, 이미 삭제된 게시글입니다.", "/cermain.do", Method.GET, null, model);
//			return "redirect:/cermain.do";
		}
		model.addAttribute("cerDTO", cerDTO);

		return "/cerdetail";
	}

	@PostMapping(value = "/cerdelete.do")
	public String deletePost(@RequestParam(value = "cernum", required = false) Long cernum, Model model) {
		System.out.println("/cerdelete.do 접근 --->" + cernum);
		// 올바르지 않은 접근 시
		if (cernum == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/cermain.do", Method.GET, null, model);
		}
		try {
			System.out.println("try 접근. cernum = " + cernum);
			boolean isDeleted = certificateService.deleteCer(cernum);
			System.out.println("deleteCer 실행 후. isDeleted = " + isDeleted);

			// false면 이미 게시글이 삭제된 상태
			if (isDeleted == false) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/cermain.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터 처리에 실패했습니다.", "/cermain.do", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/cermain.do", Method.GET, null, model);
		}
		logger.info("cernum" + cernum);
		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/cermain.do", Method.GET, null, model);
	}

	@GetMapping(value = "/error.do")
	public String openErrorList() {

		return "/error";
	}

}
