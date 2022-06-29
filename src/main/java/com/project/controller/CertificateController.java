package com.project.controller;

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

import com.project.constant.Method;
import com.project.domain.CertificateDTO;
import com.project.domain.FileDTO;
import com.project.domain.PostDTO;
import com.project.domain.TeamMemberDTO;
import com.project.domain.UserDTO;
import com.project.service.CertificateService;
import com.project.util.FileUtil;
import com.project.util.UiUtils;

// 구매 인증

@Controller
public class CertificateController extends UiUtils {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CertificateService certificateService;

	@GetMapping(value = "/productcheck.do")
	public String openProductCheck(@ModelAttribute("userId") UserDTO userId,
			@RequestParam(value = "cernum", required = false) Long cernum,
			@RequestParam(value = "pnum", required = false) Long pnum, CertificateDTO params, Model model) {

		logger.info("" + params);
		logger.info("" + pnum);
		logger.info("" + userId);
		if (pnum == null || userId == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/main.do", Method.GET, null, model);
		}
		if (cernum == null) {
			model.addAttribute("certificate", new CertificateDTO());
		}

//		try {
//			System.out.println("registerCheckService");
//			certificateService.registerCheck(params);
//
//		} catch (DataAccessException e) { // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는메시지를 전달
//			System.out.println("<-----데이터베이스 처리 과정 문제 발생----->");
//		} catch (Exception e) { // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
//			System.out.println("<-----시스템에 문제 발생----->");
//		}

		return "/checkout";
//		return "redirect:/registercheck.do?userId=" + userId;
	}

	@GetMapping("/registercheck.do")
	public String openCheckRegister(final CertificateDTO params, MultipartFile file) {
		try { // 파일업로드
			if (!file.isEmpty()) {
				FileUtil fileUtil = new FileUtil();
				FileDTO fileDTO = fileUtil.fileUpload(file);
				System.out.println("저장된 filevo: " + fileDTO.toString());
				System.out.println("저장된 file이름: " + fileDTO.getSaveName());

				// 블로그 logo-name 설정
				params.setCfile(fileDTO.getSaveName());

				System.out.println("registerCheckService");
				certificateService.registerCheck(params);
				}
		} catch (DataAccessException e) { // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는메시지를 전달
			System.out.println("<-----데이터베이스 처리 과정 문제 발생----->");
		} catch (Exception e) { // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
			System.out.println("<-----시스템에 문제 발생----->");
		}
		
		return "/checkout";
	}

}
