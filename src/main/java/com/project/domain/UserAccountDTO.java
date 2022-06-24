package com.project.domain;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAccountDTO {
	private Long historyNum;
	private String userId;
	private Long pnum;
	private String dealName;
	private float money;
	private String content;
	private int totalPoint;
	private LocalDateTime regDate;
}
