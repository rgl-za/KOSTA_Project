package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.domain.CerDTO;
import com.project.domain.CerFileDTO;
import com.project.mapper.CerMapper;
import com.project.util.FileUtils;

@Service
public class CerServiceImpl implements CerService {

	@Autowired
	private CerMapper cerMapper;

	@Autowired
	private FileUtils fileUtils;

	@Override
	public List<CerDTO> selectCerList() throws Exception {
		return cerMapper.selectCerList();
	}

	@Override
	public void insertCer(CerDTO cer, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		cerMapper.insertCer(cer);
		List<CerFileDTO> list = fileUtils.parseFileInfo(cer.getCernum(), multipartHttpServletRequest);
		if (CollectionUtils.isEmpty(list) == false) {
			cerMapper.insertCerFileList(list);
		}
	}

	@Override
	public CerDTO selectCerDetail(int cernum) throws Exception {
		CerDTO cer = cerMapper.selectBoardDetail(cernum);
		List<CerFileDTO> fileList = cerMapper.selectCerFileList(cernum);
		cer.setFileList(fileList);

		cerMapper.updateHitCount(cernum);

		return cer;
	}

	@Override
	public void updateCer(CerDTO cer) throws Exception {
		cerMapper.updateCer(cer);
	}

	@Override
	public void deleteCer(int cernum) throws Exception {
		cerMapper.deleteCer(cernum);
	}

	@Override
	public CerFileDTO selectCerFileInformation(int fidx, int cernum) throws Exception {
		return cerMapper.selectCerFileInformation(fidx, cernum);
	}
}
