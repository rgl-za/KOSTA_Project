package com.project.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.project.domain.UserDTO;
import com.project.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	
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
	public void joinUser(UserDTO userDTO) {
        userMapper.saveUser(userDTO);
	}
	
	public int overlappedID(String userid) {
		int result = userMapper.overlappedID(userid);
		
		return result;
	}
	



	


	
	


}
