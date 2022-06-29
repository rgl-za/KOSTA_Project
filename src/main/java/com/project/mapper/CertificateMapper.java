package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.CertificateDTO;

@Mapper
public interface CertificateMapper {
	
	// 인증서 등록하기
	public void registerCheck(CertificateDTO params);
	
	// 인증서 목록 불러오기
	public List<CertificateDTO> selectCheckList(Long pnum);

	
}
