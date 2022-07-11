package com.project.mapper;

import com.project.domain.HeartDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HeartMapper {
    public List<HeartDTO> selectHeartList(HeartDTO params);
    public void insertHeart(HeartDTO params);
    public int checkHeart(@Param("pnum") int pnum, @Param("userId") String userId);
}
