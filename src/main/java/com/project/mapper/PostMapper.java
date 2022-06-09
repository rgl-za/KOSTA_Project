package com.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.PostDTO;

@Mapper
public interface PostMapper {
	 public int insertPost(PostDTO params); // 인서트

	 public int updatePost(PostDTO params); // 업데이트
	 

}
