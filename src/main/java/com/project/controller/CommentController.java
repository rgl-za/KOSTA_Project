package com.project.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.project.domain.CommentDTO;
import com.project.domain.PostDTO;
import com.project.domain.UserDTO;
import com.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments.do")
    private String insertComment(@RequestParam(value = "pnum", required = false) Long pnum, CommentDTO params,
                                 @AuthenticationPrincipal UserDTO userDTO){

        params.setWriter(userDTO.getUserid());
        commentService.registerComment(params);
        return "redirect:/detail.do?pnum="+ params.getPnum();
    }

}
