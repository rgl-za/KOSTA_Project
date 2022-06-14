package com.project.service;


import com.project.domain.UserDTO;

public interface UserService {

	public void insertUser(UserDTO userDTO);
	
	public void signUpUser(UserDTO userDTO);


}
