package com.project.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// 구매 인증
@Data
public class CerDTO {
	
	/* pk */
	private int cernum;	
    
	/* postnum */
	private int pnum;		
    
	/* 인증할 사진 */
	private String cfile;	
    
	/* 업로드날짜 */
    private String regdate;
    
    /* 유저 아이디 */
    private String userId;
    
    /* 인증 제목 */
    private String title;
    
    /* 인증 내용 */
    private String content;
    
	private int hitCnt;
    
    /* deleteyn */
    private String deleteyn;
    
    /* deletetime */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") 
    private LocalDateTime deletetime;
    
    private List<CerFileDTO> fileList;
    
	private String creatorId;
	
	private String createdDatetime;
	
	private String updaterId;
	
	private String updatedDatetime;
    
}
