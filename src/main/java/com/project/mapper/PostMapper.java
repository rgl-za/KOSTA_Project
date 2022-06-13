package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.PostDTO;

@Mapper
public interface PostMapper {
	// 등록
	public int insertPost(PostDTO params);

	// detail 상세내용
	public PostDTO selectPostDetail(Long pnum);

	// 수정
	public int updatePost(PostDTO params);

	//삭제-------------------------
	public int deletePost(Long pnum);

	// main에 보낸다
	public List<PostDTO> selectPostList();

	// 글 총 개수
	public int selectPo stTotalCount();
	
	public PostDTO getPost(int pnum);
}
