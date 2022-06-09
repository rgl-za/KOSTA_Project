package com.project.domain;

import lombok.Data;

@Data
public class DealHistoryDTO{

	private String userId;
	private Long pnum;
	private int money;
	private String title;
	private String photo;
	private String dealaddress;
	private String finaldate;
	

}
