package com.project.controller;

import com.project.domain.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.domain.TeamMemberDTO;
import com.project.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class TeamMemberController {

    @Autowired
    private TeamMemberService teamMemberService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/checkTeamMember")
    @ResponseBody
    private int checkTeamMember(@RequestParam(value = "userId") String userId, @RequestParam(value = "pnum") Long pnum){
        int result = teamMemberService.checkTeamMember(userId, pnum);
        return result;
    }

    @PostMapping(value = "/team.do")
    private String insertTeamMember(@RequestParam(value = "pnum", required = false) Long pnum, TeamMemberDTO params,
                                    @AuthenticationPrincipal UserDTO userDTO) {
        logger.info("" + params);
        System.out.println("참가하기: " + pnum);
        // params.setUserId(((UserDTO) session.getAttribute("userDTO")).getUserid());
        params.setUserId(userDTO.getUserid());
        try{
        	
            teamMemberService.registerTeamMember(params);

        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());

        } finally {
            System.out.println("error");
        }
        return "redirect:/detail.do?pnum="+pnum;
    }


}
