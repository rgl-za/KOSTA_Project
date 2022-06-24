package com.project.service;

import java.util.List;

import com.project.domain.CatDTO;

public interface CatService {
	
	  /* 오늘의 커피 추첨 대상 목록 */
	  public List<CatDTO> selectCatList(CatDTO catnum); // 카테고리 목록
}
