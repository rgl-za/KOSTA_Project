package com.project.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.HeartDTO;
import com.project.mapper.HeartMapper;

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

    @Override
    public int checkHeart (int pnum, String userId) {
        System.out.println("checjkj"+pnum +"ccc" + userId);
        int result = heartMapper.checkHeart(pnum, userId);
        System.out.println("pnu333m: "+ pnum);
        return result;
    }
}
