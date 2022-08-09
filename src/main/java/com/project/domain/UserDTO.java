package com.project.domain;

import java.util.Collection;
import java.util.Collections;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class UserDTO implements UserDetails{

	
	@NotBlank(message = "아이디를 입력해주세요")
	@Pattern(regexp = "[A-Za-z0-9]{4,15}$", message = "아이디는 영어, 숫자 4 ~15자리로 입력 가능합니다")
	private String userid;     //유저 아이디 
	
	@NotBlank(message = "이름을 입력해주세요")
	private String username;   //유저 이름
	
	@NotBlank(message = "비밀번호를 입력해주세요")
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
    message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
	private String password;	   //비밀번호
	
	@NotBlank(message = "전화번호를 입력해주세요")
	@Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
	private String phone;		   //전화번호
	
	@NotBlank(message = "주소를 입력해주세요")
	private String postnum;	   //우편번호
	
	@NotBlank(message = "상세주소를 입력해주세요")
	private String address;	   //상세 주소
	
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
	@NotBlank(message = "별명을 입력해주세요")
	private String nickname;   //별명
	
	@NotBlank(message = "성별을 체크해주세요")
	private String gender;	   //성별
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "생일을 입력해주세요")
	private String birth;	   //주민번호 앞 자리
	
	private String realaddress;
	
	private String userauth;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singletonList(new SimpleGrantedAuthority(this.userauth));
	}
	
	


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	

	}

	
	
	
		
	