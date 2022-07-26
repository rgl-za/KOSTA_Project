package com.project.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PostDTO {
	// 게시글 고유 번호
	private Long pnum;

	// 카테고리 고유 번호
	private Long catnum;

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
	private Long price;

	/* private int price; */

	// 우편번호
	private int postnum;

	// 거래 주소
	private String dealaddress;

	// 최대 인원
	private Long maxpeople;

	/* private int maxpeople; */

	// 최소 인원
	/* private int minpeople; */
	private Long minpeople;

	// 게시글 전용 계좌
	private String accountpost;

	// 게시글 업로드 날짜
	private LocalDateTime uploaddate;

	// 삭제 키
	private String deleteyn;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") 
	private LocalDateTime deletetime;
	
	// 게시글 거래 마감 날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") 
	private LocalDateTime enddate;
	
	// 거래가 끝난후 방장이 end버튼을 눌렀을때 입력되는 날짜 데이터
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") 
	private LocalDateTime finaldate;
	
	// 물품 개수
	private Long numbers;
	


}