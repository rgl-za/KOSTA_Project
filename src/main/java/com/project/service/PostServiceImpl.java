
package com.project.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.PostDTO;
import com.project.mapper.PostMapper;
import com.project.mapper.TeamMemberMapper;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostMapper postMapper;
	
	private TeamMemberMapper teamMemberMapper;

	// 게시글 등록, 수정
	@Override
	public int registerPost(PostDTO params) {
		//int queryResult = 0;
		
		int pnum=0;
		System.out.println("registerPost 에 들어옴");

		if (params.getPnum() == null) {
			//queryResult = postMapper.insertPost(params);
			pnum = postMapper.insertPost(params);
			long pnum2 = params.getPnum();
			
			System.out.println("registerPost 에 들어옴"+pnum2);
			
			return (int) pnum2;
			
		} else {
			System.out.println("수정");
			//수정이면 return 1
			pnum = postMapper.updatePost(params); 
		}
		return pnum;
		//return (queryResult == 1) ? true : false;
	}
	// 사진 가져오기
	/*
	 * public List<PostDTO> getPostFile(){ return postMapper.getPostFile(); }
	 */
	
	// 상세내용에 불러올 글
	@Override
	public PostDTO getPostDetail(Long pnum) {
		return postMapper.selectPostDetail(pnum);
	}

	@Override
	public boolean deletePost(Long pnum) {
		int queryResult = 0;

		PostDTO post = postMapper.selectPostDetail(pnum);

		System.out.println("!!!!!"+post.getDeleteyn());
		System.out.println("ㅡㅡ"+pnum);
		System.out.println("@@@@"+post.getTitle());

		if (post != null && "N".equals(post.getDeleteyn())) {

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
	public List<PostDTO> getSearchPostList(String keyword, String category, String sortopt) {
		
		System.out.println("sortopt: " + sortopt);
		int cateNum;
		String keywords;
		HashMap<String, Object> map = new HashMap();
		List<PostDTO> postList = Collections.emptyList();
		
		if (keyword == null) {
			keywords = "";
			cateNum = Integer.parseInt(category);
			map.put("keyword", keywords);
			map.put("catenum", cateNum);
		}else {
			keywords = keyword;
			cateNum = Integer.parseInt(category);
			map.put("keyword", keywords);
			map.put("catenum", cateNum);
		}
		
		/*
		 * if (category == null) { cateNum = 0; map.put("keyword", keyword);
		 * map.put("catenum", cateNum); } else { cateNum = Integer.parseInt(category);
		 * map.put("keyword", keyword); map.put("catenum", cateNum); }
		 */

		try {
			System.out.println("popular! " + sortopt);
			
			// string 객체검색 할때는 equals 사용!!
			if("popular".equals(sortopt)) {
				System.out.println("popular");
				postList = postMapper.getSearchPostListPopular(map);
			}else {
				System.out.println("Latest");
				postList = postMapper.getSearchPostListLatest(map);
			}

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
