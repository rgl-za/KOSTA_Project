package com.project.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private Long cnum;
    private Long pnum;
    private String comments;
    private String writer;
    private LocalDateTime regDate;
}
