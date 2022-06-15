package com.project.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HeartDTO {
    private long lnum;
    private long pnum;
    private String userId;

    // 찜 불러오기
    private String title;
    private String content;
    private String photo;

    // 모달창
    private String leaderId;
    private String link;
    private LocalDateTime endDate;

}
