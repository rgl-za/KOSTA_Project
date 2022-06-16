package com.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.domain.TeamMemberDTO;
import com.project.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeamMemberController {

    @Autowired
    private TeamMemberService teamMemberService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/team.do")
    private void insertTeamMember(TeamMemberDTO params) {
        logger.info("" + params);
        try{
            System.out.println("ㅗㅗㅗㅗㅗㅗㅗ");
            teamMemberService.registerTeamMember(params);

        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());

        } finally {
            System.out.println("TQTTQtqtqtq");
        }
    }
}
