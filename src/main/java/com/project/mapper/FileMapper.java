
package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.FileDTO;
import com.project.domain.PostDTO;

@Mapper
public interface FileMapper { 

	public int insertFile(List<FileDTO> FileList);

	public int updateFile(Long pnum);
	
	public List<FileDTO> selectFileList(Long pnum);

}
