package com.project.mapper;

import com.project.domain.TeamMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMemberMapper {
    public List<TeamMemberDTO> selectTeamMemberList(Long pnum);
    public void insertTeamMember(TeamMemberDTO params);
    public int selectTeamMemberTotalCount(Long pnum);
    public int checkTeamMember(@Param("userId") String userId, @Param("pnum") Long pnum);
}
