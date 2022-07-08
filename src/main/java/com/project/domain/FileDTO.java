package com.project.domain;

import lombok.Data;

@Data
public class FileDTO {
	
	  private Long fnum; private Long pnum;
	 

	private String orgName;
	private String exName;

	private String saveName;
	private String filePath;
	private long fileSize;
	
	
	public FileDTO(String orgName, String exName, String saveName, String filePath, long fileSize) {
		super();
		this.orgName = orgName;
		this.exName = exName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	
}