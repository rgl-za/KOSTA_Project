package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.CertificateDTO;

@Mapper
public interface CertificateMapper {

	// 인증서 등록하기
	public int insertCer(CertificateDTO params) throws Exception;
	
	// detail 상세내용
	public CertificateDTO selectCerDetail(Long cernum) throws Exception;

	// 수정
	public int updateCer(CertificateDTO params) throws Exception;

	// 삭제-------------------------
	public int deleteCer(Long cernum) throws Exception;
	
	// 인증서 리스트 폼 --->
	// 인증서 목록 불러오기
	public List<CertificateDTO> getCerList() throws Exception;
	
}
