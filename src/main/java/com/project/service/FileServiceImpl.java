package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.FileDTO;
import com.project.mapper.FileMapper;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	private FileMapper fileMapper;
	
//	public List<FileDTO> selectFileList(FileDTO fnum){
//		List<FileDTO> filelist = fileMapper.selectFileList(fnum);
//		
//		
//		return filelist;
//	}
}
