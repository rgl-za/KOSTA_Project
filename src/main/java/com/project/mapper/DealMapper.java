package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.DealHistoryDTO;

@Mapper
public interface DealMapper {
	
	public List<DealHistoryDTO> selectEndDealList(String userId); // 나의 거래완료 내역
	public List<DealHistoryDTO> selectDealingList(String userId); // 나의 거래중인 내역

}
