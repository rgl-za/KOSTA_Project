package com.project.domain;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DealHistoryDTO{

	private String userId;
	private Long pnum;
	private int money;
	private int price;
	private String title;
	private String photo;
	private String dealaddress;
	private int postnum;
	private String link;
	private LocalDateTime finaldate;
	private LocalDateTime enddate;
	private LocalDateTime uploaddate;
	private int membercount;
	private int maxpeople;
	

}
