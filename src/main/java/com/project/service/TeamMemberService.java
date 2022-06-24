package com.project.service;

import com.project.domain.TeamMemberDTO;

import java.util.List;

public interface TeamMemberService {
    public List<TeamMemberDTO> getTeamMembertList(Long pnum);
    public void registerTeamMember(TeamMemberDTO params);
    public int selectTeamMemberTotalCount(Long pnum);
}
