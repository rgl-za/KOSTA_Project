package com.project.mapper;

import java.util.List;

import com.project.domain.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import com.project.domain.CertificateDTO;

@Mapper
public interface CertificateMapper {

	// 인증서 등록하기
	public void insertCer(CertificateDTO params);

}
