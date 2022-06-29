package com.project.service;

import java.util.List;

import com.project.domain.CertificateDTO;

public interface CertificateService {
	
	// 게시글 등록, 수정
	public void registerCheck(CertificateDTO params);
	
	public List<CertificateDTO> selectCheckList(Long pnum);
	
}
