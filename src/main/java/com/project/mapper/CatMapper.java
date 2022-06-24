package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.CatDTO;

@Mapper
public interface CatMapper {
	
	// 카테고리 index를 불러오기 
	public List<CatDTO> selectCatList(CatDTO catnum);
	
}
