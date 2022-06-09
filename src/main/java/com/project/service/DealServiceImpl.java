package com.project.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.domain.DealHistoryDTO;
import com.project.domain.PostDTO;
import com.project.mapper.DealMapper;
import com.project.mapper.PostMapper;

public class DealServiceImpl implements DealService {
	
	@Autowired
	private DealMapper dealMapper;
	
	@Override
	public List<DealHistoryDTO> getEndDealList(String userId) {
		
		List<DealHistoryDTO> endDealList = dealMapper.selectEndDealList(userId);

		return endDealList;
	}
	
	@Override
	public List<DealHistoryDTO> getDealingList(String userId) {

		List<DealHistoryDTO> dealingList = dealMapper.selectDealingList(userId);


		return dealingList;
	}

}
