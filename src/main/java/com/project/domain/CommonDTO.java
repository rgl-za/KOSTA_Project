package com.project.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO {

	/** 삭제 여부 */
	private String deleteYn;

	/** 등록일 */
	private LocalDateTime insertTime;

	/** 수정일 */
	private LocalDateTime updateTime;

	/** 삭제일 */
	private LocalDateTime deleteTime;
}
