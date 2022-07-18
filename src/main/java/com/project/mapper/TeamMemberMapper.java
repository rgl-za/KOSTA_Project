package com.project.mapper;

import com.project.domain.TeamMemberDTO;
import com.project.domain.UserDTO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMemberMapper {
    public List<UserDTO> selectTeamMemberList(Long pnum);
    public List<UserDTO> selectOtherMemberList(Long pnum);
    public void insertTeamMember(TeamMemberDTO params);
    public int selectTeamMemberTotalCount(Long pnum);
    public int checkTeamMember(@Param("userId") String userId, @Param("pnum") Long pnum);
}
