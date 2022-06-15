package com.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.CommentDTO;

@Mapper
public interface CommentMapper {
    public int insertComment(CommentDTO params);
    public int insertReply(CommentDTO params);
//    public List<CommonDTO> readCommentsByPostNumber(@Param("pnum") Long pnum, )
}
