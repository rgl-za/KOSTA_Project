package com.project.domain;

import java.util.List;

import lombok.Data;

@Data
public class PostDTO {
	// 게시글 고유 번호
	private Long pnum;
	
	// 카테고리 고유 번호
	private Long catNum;
	
	// 방장 아이디
	private String leaderid;
	
	// 게시글 제목
	private String title;
	
	// 게시글 내용
	private String content;
	
	// 사진 업로드
	private String photo;
	
	// 제품 링크
	private String link;
	
	// 제품 원가
	private int price;
	
	// 우편번호
	private int postnum;
	
	// 거래 주소
	private String dealaddress;
	
	// 최대 인원
	private int maxpeople;
	
	// 최소 인원
	private int minpeople;
	
	// 게시글 전용 계좌
	private String accountpost;
	
	// 게시글 업로드 날짜
	private String uploaddate;
	
	// 게시글 거래 마감 날짜
	private String enddate;
	
	// 삭제 키
	private String delete_yn;
	
	// finaldate
	private String finaldate;
	
	
}