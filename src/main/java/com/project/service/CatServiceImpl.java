package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.CatDTO;
import com.project.mapper.CatMapper;

@Service
public class CatServiceImpl implements CatService{
	
	@Autowired
	private CatMapper catMapper;
	
	public List<CatDTO> selectCatList(CatDTO catnum){
		
		List<CatDTO> catList = catMapper.selectCatList(catnum);
		
		return catList;
	}

}
