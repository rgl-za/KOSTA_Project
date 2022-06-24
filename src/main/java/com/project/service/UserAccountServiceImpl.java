package com.project.service;

import com.project.domain.UserAccountDTO;
import com.project.mapper.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService{

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public void registerUserAccount (UserAccountDTO params){
        userAccountMapper.insertUserAccount(params);
    }
}
