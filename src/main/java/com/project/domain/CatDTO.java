package com.project.domain;

import lombok.Data;

// 문정현 write.do에서 카테고리 선택을 위해서 만들었습니다
@Data
public class CatDTO {

    // 카테고리 번호
    private Long catnum;

    // 카테고리 이름
    private String catindex;
}
