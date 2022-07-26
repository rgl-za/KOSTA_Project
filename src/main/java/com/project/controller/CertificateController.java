package com.project.controller;

import java.util.List;

import com.project.domain.PostDTO;
import com.project.domain.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.constant.Method;
import com.project.domain.CertificateDTO;
import com.project.domain.FileDTO;
import com.project.service.CertificateService;
import com.project.util.FileUtil;
import com.project.util.UiUtils;

@Controller
public class CertificateController extends UiUtils {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CertificateService certificateService;

	@ResponseBody
	@PostMapping(value = "/cerwrite.do")
	public String openCerWrite(@ModelAttribute("params") CertificateDTO params,
							   @AuthenticationPrincipal UserDTO userDTO, Model model){

			params.setUserId(userDTO.getUserid());
			certificateService.registerCer(params);

		return "/mydealhistory";
	}
}
