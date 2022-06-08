package com.project.domain;

import lombok.Data;

@Data
public class PostDTO extends CommonDTO {

  private Long pnum; 			// 포스트번호 (PK)
  private String leaderid; 		// 방장 아이디
  private String title; 		// 제목
  private String content; 		// 내용
  private String photo; 		// 사진
  private String link; 			// 링크
  private int price; 			// 가격
  private int maxpeople; 		// 최대인원
  private int minpeople; 		// 최소인원
  private String accountpost; 	// 포스트 개수?
  private String uploaddate; 	// 시작 날짜
  private String enddate; 		// 끝나는 날짜
}
