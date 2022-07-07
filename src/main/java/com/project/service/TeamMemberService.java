package com.project.service;

import java.util.List;

import com.project.domain.TeamMemberDTO;

public interface TeamMemberService {
    public List<TeamMemberDTO> getTeamMembertList(Long pnum);
    public void registerTeamMember(TeamMemberDTO params);
    public int selectTeamMemberTotalCount(Long pnum);
    public int checkTeamMember(String userId, Long pnum);
}
