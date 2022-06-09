package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.PostDTO;

@Mapper
public interface PostMapper {
	public int insertPost(PostDTO params);

	public PostDTO selectPostDetail(Long pnum);

	public int updatePost(PostDTO params);

	public int deletePost(Long pnum);

	public List<PostDTO> selectPostList();

	public int selectPostTotalCount();
	
	public PostDTO getPost(int pnum);
}
