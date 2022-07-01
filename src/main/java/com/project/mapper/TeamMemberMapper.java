package com.project.mapper;

import com.project.domain.TeamMemberDTO;
import com.project.domain.UserDTO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMemberMapper {
    public List<UserDTO> selectTeamMemberList(Long pnum);
    public void insertTeamMember(TeamMemberDTO params);
    public int selectTeamMemberTotalCount(Long pnum);
}
