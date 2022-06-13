package com.project.domain;

import lombok.Data;

@Data
public class CommentDTO {

    private Long cnum;
    private Long pnum;
    private Long commentParentnum;
    private String commentContent;
    private String commentWriter;
    private String commentDate;
    private boolean commentDelete;
}
