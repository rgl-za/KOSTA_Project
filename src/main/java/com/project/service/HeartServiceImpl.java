package com.project.service;

import com.project.domain.HeartDTO;
import com.project.mapper.HeartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class HeartServiceImpl implements HeartService{
    @Autowired
    private HeartMapper heartMapper;

    @Override
    public void registerHeart (HeartDTO params){
        heartMapper.insertHeart(params);
    }

    @Override
    public List<HeartDTO> getHeartList (HeartDTO params) {
        List<HeartDTO> heartList = Collections.emptyList();
        heartList = heartMapper.selectHeartList(params);
        return heartList;
    }
}
