package com.project.mapper;

import com.project.domain.CommentDTO;
import com.project.domain.CommonDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    public int insertComment(CommentDTO params);
    public int insertReply(CommentDTO params);
//    public List<CommonDTO> readCommentsByPostNumber(@Param("pnum") Long pnum, )
}
