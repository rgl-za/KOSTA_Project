package com.project.service;

import java.util.List;

import com.project.domain.CertificateDTO;
import com.project.domain.PostDTO;
import com.project.domain.TeamMemberDTO;

public interface PostService {

	// 게시글 등록, 수정
	public boolean registerPost(PostDTO params, TeamMemberDTO captain); 


	// 게시글 상세 내용 불러오기
	public PostDTO getPostDetail(Long pnum);

	// 게시글 삭제----------------------------
	public boolean deletePost(Long pnum);

	// 사진 불러오기
	/* public List<PostDTO> getPostFile(); */

	// 게시글 메인에 불러오기	
	public List<PostDTO> getPostList();
	
	// 게시물 검색, 정렬
    public List<PostDTO> getSearchPostList(String keyword, String category, String sortopt);
	
	public boolean alterDealAdd(PostDTO params);
	
	public boolean pushFinaldate(CertificateDTO params);

	public List<PostDTO> recommendPostList(Long recommendItem);
	
	
	// 게시글 사진 불러오기
	
	/*
	 * //게시글 1개 불러오기(detail.html에서 사용) public PostDTO getPost(int pnum);
	 */

}
