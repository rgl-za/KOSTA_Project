package com.project.domain;

import lombok.Data;

@Data
public class AttachDTO {
	
	/** 파일 번호 (PK) */
	private Long fidx;

	/** 게시글 번호 (FK) */
	private Long pnum;

	/** 원본 파일명 */
	private String originalName;

	/** 저장 파일명 */
	private String saveName;

	/** 파일 크기 */
	private long fsize;
	
}
