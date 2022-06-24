package com.project.controller;

import com.project.domain.UserAccountDTO;
import com.project.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping(value="/account.do")
    private String insertUserAccount(@RequestParam(value = "pnum", required = false) Long pnum, UserAccountDTO params){
        System.out.println("참가유저 등록 게시판: " + pnum);
        System.out.println("!!!!!@@@@"+params);

        try{
            System.out.println("ㅗㅗㅗㅗㅗㅗㅗ");
            userAccountService.registerUserAccount(params);


        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());

        } finally {
            System.out.println("TQTTQtqtqtq");
        }
        return "redirect:/detail.do?pnum="+pnum;
    }
}
