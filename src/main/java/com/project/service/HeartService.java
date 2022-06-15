package com.project.service;

import com.project.domain.HeartDTO;

import java.util.List;

public interface HeartService {
    public List<HeartDTO> getHeartList(HeartDTO params);
    public void registerHeart(HeartDTO params);
}
