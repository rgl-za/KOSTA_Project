package com.project.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.TeamMemberDTO;
import com.project.mapper.TeamMemberMapper;

@Service
public class TeamMemberServiceImpl implements TeamMemberService{

    @Autowired
    private TeamMemberMapper teamMemberMapper;

    @Override
    public void registerTeamMember (TeamMemberDTO params){
        teamMemberMapper.insertTeamMember(params);
    }
    
    //팀멤버 리스트
    @Override
    public List<TeamMemberDTO> getTeamMembertList (Long pnum) {
        List<TeamMemberDTO> teamMemberList = Collections.emptyList();
        teamMemberList = teamMemberMapper.selectTeamMemberList(pnum);
        return teamMemberList;
    }

    @Override
    public int selectTeamMemberTotalCount(Long pnum) {
        int memberCount = teamMemberMapper.selectTeamMemberTotalCount(pnum);
        return memberCount;
    }

    @Override
    public int checkTeamMember(String userId, Long pnum){
        int result = teamMemberMapper.checkTeamMember(userId, pnum);
        System.out.println("result: " + result);
        return result;
    }
}
