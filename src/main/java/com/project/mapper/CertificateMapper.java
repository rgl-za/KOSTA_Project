package com.project.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.CertificateDTO;
import com.project.domain.PostDTO;

@Mapper
public interface CertificateMapper {

	// 인증서 등록하기
	public int insertCer(CertificateDTO params);
	
	// detail 상세내용
	public CertificateDTO selectCerDetail(Long cernum);

	// 수정
	public int updateCer(CertificateDTO params);

	// 삭제-------------------------
	public int deleteCer(Long cernum);
	
	// 인증서 리스트 폼 --->
	// 인증서 목록 불러오기
	public List<CertificateDTO> selectCerList();
	
	// 게시물 검색
	public List<CertificateDTO> getSearchCerList(HashMap<String, Object> map);
}
