package com.project.controller;

import com.project.domain.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.domain.TeamMemberDTO;
import com.project.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class TeamMemberController {

    @Autowired
    private TeamMemberService teamMemberService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/team.do")
    private String insertTeamMember(@RequestParam(value = "pnum", required = false) Long pnum, TeamMemberDTO params, HttpSession session) {
        logger.info("" + params);
        System.out.println("참가하기: " + pnum);
        params.setUserId(((UserDTO) session.getAttribute("userDTO")).getUserid());
        try{
            System.out.println("ㅗㅗㅗㅗㅗㅗㅗ");
            teamMemberService.registerTeamMember(params);

        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());

        } finally {
            System.out.println("TQTTQtqtqtq");
        }
        return "redirect:/detail.do?pnum="+pnum;
    }


}
