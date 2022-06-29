package com.project.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.CertificateDTO;
import com.project.mapper.CertificateMapper;

@Service
public class CertificateServiceImpl implements CertificateService {
	
	@Autowired
	private CertificateMapper certificateMapper;
	
	@Override
    public void registerCheck (CertificateDTO params){
        certificateMapper.registerCheck(params);
    }

    @Override
    public List<CertificateDTO> selectCheckList (Long cernum) {
        List<CertificateDTO> checkList = Collections.emptyList();
        checkList = certificateMapper.selectCheckList(cernum);
        return checkList;
    }
	
	
}
