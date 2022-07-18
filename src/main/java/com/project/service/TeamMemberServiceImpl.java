package com.project.service;

import com.project.domain.TeamMemberDTO;
import com.project.domain.UserDTO;
import com.project.mapper.TeamMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
    public List<UserDTO> getTeamMembertList (Long pnum) {
        List<UserDTO> teamMemberList = Collections.emptyList();
        teamMemberList = teamMemberMapper.selectTeamMemberList(pnum);
        return teamMemberList;
    }
    //방장을 제외한 팀 맴버 리스트
    @Override
    public List<UserDTO> getOtherMembertList (Long pnum) {
        List<UserDTO> teamMemberList = Collections.emptyList();
        teamMemberList = teamMemberMapper.selectOtherMemberList(pnum);
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
