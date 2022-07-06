package com.project.domain;

import lombok.Data;

@Data
public class CerFileDTO {
	
	private int fidx;
	
	private int cernum;
	
	private String originalFileName;
	
	private String storedFilePath;
	
	private long fileSize;
}
