package com.project.service;

import com.project.domain.TeamMemberDTO;
import com.project.domain.UserDTO;

import java.util.List;

public interface TeamMemberService {
    public List<UserDTO> getTeamMembertList(Long pnum);
    public List<UserDTO> getOtherMembertList(Long pnum);
    public void registerTeamMember(TeamMemberDTO params);
    public int selectTeamMemberTotalCount(Long pnum);
    public int checkTeamMember(String userId, Long pnum);
}
