
package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.FileDTO;

@Mapper
public interface FileMapper { 

	public int insertFile(List<FileDTO> FileList);

	public int updateFile(Long pnum);
	
	public List<FileDTO> selectFileList(Long pnum);

//	public List<FileDTO> selectFileList(FileDTO fnum);

}
