package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mapper.CatMapper;

@Service
public class CatServiceImpl implements CatService{
	
	@Autowired
	private CatMapper catMapper;

}
