package com.project.controller;

import com.project.domain.HeartDTO;
import com.project.domain.PostDTO;
import com.project.domain.UserDTO;
import com.project.service.HeartService;
import com.project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HeartController {

    @Autowired
    private HeartService heartService;

    // @Autowired
    // private PostService postService;

    @PostMapping("/checkHeart")
    @ResponseBody
    private int checkHeart(@RequestParam("pnum") int pnum, @AuthenticationPrincipal UserDTO userDTO){
        String userId =  userDTO.getUserid();
        System.out.println("userid3333"+userId);
        int result = heartService.checkHeart(pnum, userId);
        System.out.println("222"+result);
        return result;
    }

    @PostMapping("/heart.do")
    private String insertHeart(@RequestParam(value = "pnum", required = false) Long pnum, HeartDTO params,
                               @AuthenticationPrincipal UserDTO userDTO){

        // UserDTO params2 = new UserDTO();
        // params2.getUserid();

        // System.out.println("check"+(UserDTO) session.getAttribute("userDTO"));
        // UserDTO user = (UserDTO) session.getAttribute("userDTO").;
        //System.out.println("check2"+user);

        // params.setUserId(((UserDTO) session.getAttribute("userDTO")).getUserid());
        params.setUserId(userDTO.getUserid());
        System.out.println("userid!!!!!"+userDTO.getUserid());
        // params.setUserId(sec:authentication="name");
        heartService.registerHeart(params);

        return "redirect:/pick";
    }

    @GetMapping("/pick")
    // 유저 정보 수정 해야 함 @RequestParam(value = "userId", required = false) String userId, 같은...
    private String selectHeartList(HeartDTO params, Model model, @AuthenticationPrincipal UserDTO userDTO){
        params.setUserId(userDTO.getUserid());
        //params.setUserId(((UserDTO) session.getAttribute("userDTO")).getUserid());
        List<HeartDTO> heartList = heartService.getHeartList(params);
        // List<PostDTO> postList = postService.getPostList();
        // model.addAttribute("postList", postList);
        model.addAttribute("heartList",heartList);
        model.addAttribute("heart", new HeartDTO());
        // return "/pick?userId="+ userid;
        return "/pick";
    }
}
