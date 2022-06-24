package com.project.mapper;

import com.project.domain.UserAccountDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountMapper {
    public void insertUserAccount(UserAccountDTO params);
}
