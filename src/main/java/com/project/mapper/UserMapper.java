package com.project.mapper;

import java.util.Map; 

import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

import com.project.domain.UserDTO;

@Mapper
public interface UserMapper {
	
	public void saveUser(UserDTO userDTO);

	public int overlappedID(String userid);
	

	}
		
	


