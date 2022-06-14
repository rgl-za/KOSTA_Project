package com.project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.UserDTO;
import com.project.mapper.UserMapper;



@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserMapper userMapper;
	

	@Transactional
	public void insertUser(UserDTO userDTO) {
		
		userMapper.insertUser(userDTO);
	}


	@Override
	public void signUpUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
	}
	
	
}
