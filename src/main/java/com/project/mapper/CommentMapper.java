package com.project.mapper;

import com.project.domain.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    public List<CommentDTO> selectCommentList(Long pnum);
    public void insertComment(CommentDTO params);

}
