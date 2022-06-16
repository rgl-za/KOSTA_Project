package com.project.controller;

import com.project.domain.TeamMemberDTO;
import com.project.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeamMemberController {

    @Autowired
    private TeamMemberService teamMemberService;

    @PostMapping(value = "/team")
    private String insertTeamMember(@RequestParam(value = "pnum", required = false) Long pnum, TeamMemberDTO params){
        teamMemberService.registerTeamMember(params);
        return "redirect:/detail.do?pnum="+ params.getPnum();
    }
}
