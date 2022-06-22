
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

		if (post != null && "N".equals(post.getDelete_yn())) {
			queryResult = postMapper.deletePost(pnum);
		}

		return (queryResult == 1) ? true : false;
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

		//int postTotalCount = postMapper.selectPostTotalCount(option);

		/*
		 * if (postTotalCount > 0) { postList = postMapper.selectPostList(option); }
		 */
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
