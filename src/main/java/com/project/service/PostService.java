package com.project.service;

import java.util.List;

import com.project.domain.PostDTO;

public interface PostService {

	// 게시글 등록, 수정
	public boolean registerPost(PostDTO params); 

	// 게시글 상세 내용 불러오기
	public PostDTO getPostDetail(Long pnum);

	// 게시글 삭제
	public boolean deletePost(Long pnum);

	// 게시글 메인에 불러오기
	public List<PostDTO> getPostList();
	
	//게시글 1개 불러오기(detail.html에서 사용)
	public PostDTO getPost(int pnum);

}
