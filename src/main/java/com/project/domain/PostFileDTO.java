package com.project.domain;

import lombok.Data;

@Data
public class PostFileDTO {
	
	private int fidx;
	
	private int pnum;
	
	private String originalFileName;
	
	private String storedFilePath;
	
	private long fileSize;
}
