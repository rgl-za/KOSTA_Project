package com.project.service;

import java.util.List;

import com.project.domain.HeartDTO;

public interface HeartService {
    public List<HeartDTO> getHeartList(HeartDTO params);
    public void registerHeart(HeartDTO params);
    public int checkHeart(int pnum, String userId);
}
