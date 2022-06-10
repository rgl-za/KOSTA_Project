package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.DealHistoryDTO;
import com.project.domain.UserAccountDTO;

public interface DealService {

	public List<DealHistoryDTO> getEndDealList(String userId); // 나의 거래완료 내역
	public List<DealHistoryDTO> getDealingList(String userId); // 나의 거래중인 내역

}
