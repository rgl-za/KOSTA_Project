package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.domain.CerDTO;
import com.project.domain.CerFileDTO;

@Mapper
public interface CerMapper {
	List<CerDTO> selectCerList() throws Exception;

	void insertCer(CerDTO cer) throws Exception;

	CerDTO selectBoardDetail(int cernum) throws Exception;

	void updateHitCount(int cernum) throws Exception;

	void updateCer(CerDTO cer) throws Exception;

	void deleteCer(int cernum) throws Exception;

	void insertCerFileList(List<CerFileDTO> list) throws Exception;

	List<CerFileDTO> selectCerFileList(int cernum) throws Exception;

	CerFileDTO selectCerFileInformation(@Param("fidx") int fidx, @Param("cernum") int cernum);
}
