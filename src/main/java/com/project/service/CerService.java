package com.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.domain.CerDTO;
import com.project.domain.CerFileDTO;

public interface CerService {
	
	List<CerDTO> selectCerList() throws Exception;
	
	void insertCer(CerDTO cer, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

	CerDTO selectCerDetail(int cernum) throws Exception;

	void updateCer(CerDTO cernum) throws Exception;

	void deleteCer(int cernum) throws Exception;

	CerFileDTO selectCerFileInformation(int fidx, int cernum) throws Exception; 
}
