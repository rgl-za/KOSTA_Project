package com.project.domain;

import lombok.Data;

@Data
public class PostFileDTO {
	private int pnum;
	private int fnum;
	private String originalFileName;
	private String filePath;
	private long fileSize;
}
