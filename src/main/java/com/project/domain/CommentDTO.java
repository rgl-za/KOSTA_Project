package com.project.domain;

import lombok.Data;

@Data
public class CommentDTO {

    private Long cnum;
    private Long pnum;
    private String message;
    private String writer;
    private String date;
}
