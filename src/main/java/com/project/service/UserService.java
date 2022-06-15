package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.UserDTO;
import com.project.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Transactional
	public void joinUser(UserDTO userDTO) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userMapper.insertUser(userDTO);
	}

}
