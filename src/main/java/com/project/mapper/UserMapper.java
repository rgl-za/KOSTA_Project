package com.project.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.project.domain.UserDTO;

@Mapper
public interface UserMapper {
	
	public void saveUser(UserDTO userDTO);

	public int overlappedID(String userid);
	
	public UserDTO memberLogin(String userid);
	
	public int UserUpdate(UserDTO userDTO);
	

	}
		
	