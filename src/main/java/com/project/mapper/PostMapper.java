package com.project.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.PostDTO;

@Mapper
public interface PostMapper {
	/* public List<PostDTO> getPostFile(); */

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
	public int selectPostTotalCount();
	
	public PostDTO getPost(int pnum);
	
	//dealaddress 변경
	public boolean alterDealAdd(PostDTO params);
	
	// 최신순 main 정렬
	public List<PostDTO> latestPostList();
	
	// 인기순 main 정렬
	public List<PostDTO> popularPostList();
	
	// 게시물 검색 (인기순)
	public List<PostDTO> getSearchPostListPopular(HashMap<String, Object> map);
	
	// 게시물 검색 (최신순)
	public List<PostDTO> getSearchPostListLatest(HashMap<String, Object> map);

	// post 개수
	public int PostTotalCount();
	
	public boolean pushFinaldate(Long postPnum);

	public List<PostDTO> recommendPostList(Long recommendItem);

}
