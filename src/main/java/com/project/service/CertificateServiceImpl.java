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

	// create, update
	@Override
	public boolean registerCer(CertificateDTO params) throws Exception {
		int queryResult = 0;

		if (params.getCernum() == null) {
			System.out.println("insert");
			queryResult = certificateMapper.insertCer(params);
		} else {
			System.out.println("update");
			queryResult = certificateMapper.updateCer(params);
		}

		return (queryResult == 1) ? true : false;
	}

	// read
	@Override
	public CertificateDTO getCerDetail(Long cernum) throws Exception {
		return certificateMapper.selectCerDetail(cernum);
	}

	// delete
	@Override
	public boolean deleteCer(Long cernum) throws Exception {
		int queryResult = 0;

		CertificateDTO cer = certificateMapper.selectCerDetail(cernum);

		if (cer != null && "N".equals(cer.getDeleteyn())) {

			queryResult = certificateMapper.deleteCer(cernum);
		}

		return (queryResult == 1) ? true : false;
	}

	// main list
	@Override
	public List<CertificateDTO> getCerList() throws Exception {
		List<CertificateDTO> cerList = Collections.emptyList();
		
		cerList = certificateMapper.getCerList();
		
		return cerList;
	}

}
