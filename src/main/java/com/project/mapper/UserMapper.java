package com.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.UserDTO;

@Mapper
public interface UserMapper {
	
	public void insertUser(UserDTO userDTO);

}
