package com.project.service;

import java.util.List;

import com.project.domain.CommentDTO;

public interface CommentService {
    public List<CommentDTO> getCommentList(Long pnum);
    public void registerComment(CommentDTO params);
}
