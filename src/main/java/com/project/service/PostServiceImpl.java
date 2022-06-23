
package com.project.service;

import java.util.Collections;
import java.util.List;

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
		} else { 
			queryResult = postMapper.updatePost(params); 
		}

		return (queryResult == 1) ? true : false;
	}

	// 상세내용에 불러올 글
	@Override
	public PostDTO getPostDetail(Long pnum) {
		return postMapper.selectPostDetail(pnum);
	}
	
	// 게시글 삭제입니당
	@Override
	public boolean deletePost(Long pnum) {
//		int queryResult = 0;

		PostDTO post = postMapper.selectPostDetail(pnum);
		
		// 조회한 게시글이 null이 아니고, 삭제된 상태가 아닐 때 실행.
		if (post != null && "N".equals(post.getDelete_yn())) {
			postMapper.deletePost(pnum);
		}
		
		// 1이면 정상적으로 쿼리가 실행되었다는 뜻이기 때문에, true 반환.
//		System.out.println("1이면 정상적으로 쿼리 실행--------------------->"+queryResult);
		return true;
	} 
	
	// main에 불러올 글
	@Override
	public List<PostDTO> getPostList(PostDTO params) {
		List<PostDTO> postList = Collections.emptyList();

		int postTotalCount = postMapper.selectPostTotalCount(params);

		if (postTotalCount > 0) {
			postList = postMapper.selectPostList(params);
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
	public boolean alterDealAdd(PostDTO params) {
		
		return postMapper.alterDealAdd(params);
	}

	

	/*
	 * @Override public PostDTO getPost(int pnum) { PostDTO postDTO =
	 * postMapper.getPost(pnum);
	 * }
	 */

}
