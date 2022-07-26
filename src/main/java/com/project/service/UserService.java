package com.project.service;

import java.util.HashMap;
import java.util.Map;

//import org.hibernate.validator.internal.util.logging.Log_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.project.domain.UserDTO;
import com.project.mapper.UserMapper;

import ch.qos.logback.classic.Logger;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
	
	@Autowired
	UserMapper userMapper;
	
//	@Autowired
//	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	//회원가입 유효성 검사
	@Transactional(readOnly = true)
	public Map<String, String> validateHandling(Errors errors){
		Map<String, String> validtorResult = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s",error.getField());
			validtorResult.put(validKeyName, error.getDefaultMessage());
		}
		return validtorResult;
	}
	
	//회원가입
	@Transactional
	public void joinUser(UserDTO userDTO) {
		BCryptPasswordEncoder passwordencoder = new BCryptPasswordEncoder();
		userDTO.setPassword(passwordencoder.encode(userDTO.getPassword()));
        userMapper.saveUser(userDTO);
	}
	
	//아이디 중복 체크
	public int overlappedID(String userid){
		System.out.println("tq");
		int cnt = userMapper.overlappedID(userid);
		System.out.println("die:"+ cnt);
		return cnt;
	}
	
	
	//회원정보 수정
	public int UserUpdate(UserDTO userDTO) {
		return userMapper.UserUpdate(userDTO);
	}

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		
		System.out.println("$$loadUserByUsername$$");
		
		UserDTO userDTO = userMapper.memberLogin(userid);
		if(userDTO == null) {
			
			throw new UsernameNotFoundException("존재하지 않는 아이디 입니다.");
		}
		return userDTO;
	}
	
	

	
}
	


