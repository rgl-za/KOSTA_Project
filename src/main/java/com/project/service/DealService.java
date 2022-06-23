package com.project.service;

import java.util.List;

import com.project.domain.DealHistoryDTO;

public interface DealService {

	public List<DealHistoryDTO> getEndDealList(String userId); // 나의 거래완료 내역
	public List<DealHistoryDTO> getDealingList(String userId); // 나의 거래중인 내역

}
