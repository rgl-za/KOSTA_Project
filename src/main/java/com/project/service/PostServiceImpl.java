package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.PostDTO;
import com.project.mapper.PostMapper;


@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostMapper postMapper;

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

}
