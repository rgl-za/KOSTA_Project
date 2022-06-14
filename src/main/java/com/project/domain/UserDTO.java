package com.project.domain;

import lombok.Data;

@Data
public class UserDTO {
	
	private String userid;     //유저 아이디
	private String username;   //유저 이름
	private int password;	   //비밀번호
	private int phone;		   //전화번호	
	private int postnum;	   //우편번호
	private String address;	   //상세 주소
	private String nickname;   //별명	
	private String gender;	   //성별
	private String birth;	   //주민번호 앞 자리

}
