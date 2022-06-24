
package com.project.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.PostDTO;
import com.project.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostMapper postMapper;

	// 게시글 등록, 수정
	@Override
	public boolean registerPost(PostDTO params) {
		int queryResult = 0;

		if (params.getPnum() == null) {
			queryResult = postMapper.insertPost(params);
		}
		// 난수 생길 예정 } else { queryResult = postMapper.updatePost(params); }

		return (queryResult == 1) ? true : false;
	}

	// 상세내용에 불러올 글
	@Override
	public PostDTO getPostDetail(Long pnum) {
		return postMapper.selectPostDetail(pnum);
	}

	@Override
	public boolean deletePost(Long pnum) {
		int queryResult = 0;

		PostDTO post = postMapper.selectPostDetail(pnum);
		System.out.println("post.getDelete_yn(): " + post.getDelete_yn());
		if (post != null && "N".equals(post.getDelete_yn())) {
			queryResult = postMapper.deletePost(pnum);
		}

		return (queryResult == 1) ? true : false;
	}
	
	// main에 불러올 글
	@Override
	public List<PostDTO> getPostList() {
		List<PostDTO> postList = Collections.emptyList();

		int postTotalCount = postMapper.selectPostTotalCount();

		if (postTotalCount > 0) {
			postList = postMapper.selectPostList();
		}
		return postList;
	}
	
	@Override
	public List<PostDTO> getPostSortList(String option) {
		List<PostDTO> postList = Collections.emptyList();
		System.out.println("option: " + option);
		
		// 최신순
		if(option=="latest") {
			
			int postTotalCount = postMapper.PostTotalCount();

			if (postTotalCount > 0) { 
				postList = postMapper.latestPostList(); 
			}
			
			return postList;
		
		// 인기순
		}else if (option=="popular"){
			
			int postTotalCount = postMapper.PostTotalCount();

			
			if (postTotalCount > 0) { 
				postList = postMapper.popularPostList(); 
			}
			
			return postList;	
			
		}else {
			
			int postTotalCount = postMapper.PostTotalCount();

			if (postTotalCount > 0) { 
				postList = postMapper.latestPostList(); 
			}
			
			return postList;
		}
		
	}
	@Override
	public List<PostDTO> getSearchPostList(String keyword, String category) {

		int cateNum;
		HashMap<String, Object> map = new HashMap();
		List<PostDTO> postList = Collections.emptyList();

		if (category == null) {
			cateNum = 0;
			map.put("keyword", keyword);
			map.put("catenum", cateNum);
		} else {
			cateNum = Integer.parseInt(category);
			map.put("keyword", keyword);
			map.put("catenum", cateNum);
		}

		try {
			postList = postMapper.getSearchPostList(map);

		} catch (Exception e) {
			System.out.println("예외발생");
		}

		return postList;
	}
	
	
    @Override
	public boolean alterDealAdd(PostDTO params) {
		
		return postMapper.alterDealAdd(params);
	}

	

	/*
	 * @Override public PostDTO getPost(int pnum) { PostDTO postDTO =
	 * postMapper.getPost(pnum);
	 * }
	 */

}
