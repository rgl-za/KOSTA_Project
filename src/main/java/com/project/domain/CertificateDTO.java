package com.project.domain;

import lombok.Data;

// 구매 인증
@Data
public class CertificateDTO {
	
	/* pk */
	private Long cernum;	
    
	/* postnum */
	private Long pnum;		
    
	/* 인증할 사진 */
	private String cfile;	
    
	/* 업로드날짜 */
    private String regdate;
    
    /* 유저 아이디 */
    private String userId;
    
    /* 인증 내용 */
    private String content;
    
}
