package com.project.domain;

import lombok.Data;

@Data
public class UserDTO {

	//@NotBlank(message = "아이디를 입력해주세요")
	//@Pattern(regexp = "[A-Za-z0-9]{4,15}$", message = "아이디는 영어, 숫자 4 ~15자리로 입력 가능합니다")
	private String userid;     //유저 아이디 
	
	//@NotBlank(message = "이름을 입력해주세요")
	private String username;   //유저 이름
	
	//@NotBlank(message = "비밀번호를 입력해주세요")
	//@Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하로 입력해주세요")
	private String password;	   //비밀번호
	
	//@NotBlank(message = "전화번호를 입력해주세요")
	//@Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
	private int phone;		   //전화번호
	
	//@NotBlank(message = "우편번호를 입력해주세요")
	private int postnum;	   //우편번호
	
	//@NotBlank(message = "상세주소를 입력해주세요")
	private String address;	   //상세 주소
	
	//@NotBlank(message = "별명을 입력해주세요")
	private String nickname;   //별명
	
	//@NotBlank(message = "성별을 체크해주세요")
	private String gender;	   //성별
	
	//@NotBlank(message = "생일을 입력해주세요")
	private String birth;	   //주민번호 앞 자리
	
	private String realaddress;
	
		
	}
	
	
	

