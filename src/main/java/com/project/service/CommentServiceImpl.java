package com.project.service;

import com.project.domain.CommentDTO;
import com.project.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void registerComment (CommentDTO params){
        commentMapper.insertComment(params);
    }

    @Override
    public List<CommentDTO> getCommentList (Long pnum) {
        List<CommentDTO> commentList = Collections.emptyList();
        commentList = commentMapper.selectCommentList(pnum);
        return commentList;
    }
}
