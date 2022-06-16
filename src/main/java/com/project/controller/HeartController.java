package com.project.controller;

import com.project.domain.HeartDTO;
import com.project.service.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HeartController {

    @Autowired
    HeartService heartService;

    @PostMapping("/heart.do")
    private String insertHeart(@RequestParam(value = "pnum", required = false) Long pnum, HeartDTO params){
//        params.setPnum(pnum);
//        System.out.println(params.getPnum());
//        params.setWriter(); 회원 아이디 받아야함
        heartService.registerHeart(params);
        return "redirect:/pick";
    }

    @GetMapping("/pick")
    // 유저 정보 수정 해야 함 @RequestParam(value = "userId", required = false) String userId,
    private String selectHeartList(HeartDTO params, Model model){
        String userid  = "test";
        params.setUserId(userid);
        List<HeartDTO> heartList = heartService.getHeartList(params);
        model.addAttribute("heartList",heartList);
        model.addAttribute("heart", new HeartDTO());
        // return "/pick?userId="+ userid;
        return "/pick";
    }
}
