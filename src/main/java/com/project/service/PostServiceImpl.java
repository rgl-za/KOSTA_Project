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
    // 게시글 삭제--------------------------------------------------
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
	public List<PostDTO> getPostList() {
		List<PostDTO> postList = Collections.emptyList();

		int postTotalCount = postMapper.selectPostTotalCount();

		if (postTotalCount > 0) {
			postList = postMapper.selectPostList();
		}

		return postList;
	}
	
	@Override
	public PostDTO getPost(int pnum) {
		PostDTO postDTO = postMapper.getPost(pnum);
		// TODO Auto-generated method stub
		return postDTO;
	}

}