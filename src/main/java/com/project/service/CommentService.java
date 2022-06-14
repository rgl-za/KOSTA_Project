package com.project.service;

import com.project.domain.CommentDTO;

import java.util.List;

public interface CommentService {
    public List<CommentDTO> getCommentList(Long pnum);
    public void registerComment(CommentDTO params);
}
