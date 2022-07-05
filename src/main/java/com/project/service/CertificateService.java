package com.project.service;

import java.util.List;

import com.project.domain.CertificateDTO;
import com.project.domain.PostDTO;

public interface CertificateService {
	
	// 게시글 등록, 수정
	public boolean registerCer(CertificateDTO params);
	
	// 게시글 상세 내용 불러오기
	public CertificateDTO getCerDetail(Long cernum);
	
	// 수정
	//public int updateCer(CertificateDTO params);
	
	// 게시글 삭제----------------------------
	public boolean deleteCer(Long cernum);
	
	// 게시글 리스트
	public List<CertificateDTO> getCerList();
	
}
