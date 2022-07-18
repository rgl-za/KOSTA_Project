package com.project.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// 구매 인증
@Data
public class CertificateDTO {
	
	/* pk */
	private Long cernum;	
    
	/* postnum */
	private Long pnum;		
    
	/* 인증할 사진 */
	@NotBlank(message = "사진을 선택해주세요")
	private String cfile;	
    
	/* 업로드날짜 */
    private String regdate;
    
    /* 유저 아이디 */
    private String userId;
    
    /* 인증 제목 */
    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    
    /* 인증 내용 */
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    
    /* deleteyn */
    private String deleteyn;
    
    /* deletetime */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") 
    private LocalDateTime deletetime;
    
    
}
