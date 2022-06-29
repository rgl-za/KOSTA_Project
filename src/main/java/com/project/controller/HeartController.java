package com.project.controller;

import com.project.domain.HeartDTO;
import com.project.domain.UserDTO;
import com.project.service.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HeartController {

    @Autowired
    private HeartService heartService;

    @PostMapping("/heart.do")
    private String insertHeart(@RequestParam(value = "pnum", required = false) Long pnum, HeartDTO params, HttpSession session){
//        params.setPnum(pnum);
//        System.out.println(params.getPnum());
//        params.setWriter(); 회원 아이디 받아야함
        params.setUserId(((UserDTO) session.getAttribute("userDTO")).getUserid());
        heartService.registerHeart(params);
        return "redirect:/pick";
    }

    @GetMapping("/pick")
    // 유저 정보 수정 해야 함 @RequestParam(value = "userId", required = false) String userId, 같은...
    private String selectHeartList(HeartDTO params, Model model, HttpSession session){
        params.setUserId(((UserDTO) session.getAttribute("userDTO")).getUserid());
        List<HeartDTO> heartList = heartService.getHeartList(params);
        model.addAttribute("heartList",heartList);
        model.addAttribute("heart", new HeartDTO());
        // return "/pick?userId="+ userid;
        return "/pick";
    }
}
