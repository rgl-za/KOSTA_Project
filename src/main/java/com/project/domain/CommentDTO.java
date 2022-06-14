package com.project.domain;

import lombok.Data;

@Data
public class CommentDTO {

    private Long cnum;
    private Long pnum;
    private String comments;
    private String writer;
    private String regDate;
}
