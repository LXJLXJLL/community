package com.lxj.springboot.springboot.controller;

import com.lxj.springboot.springboot.dto.PaginationDTO;
import com.lxj.springboot.springboot.mapper.UserMapper;
import com.lxj.springboot.springboot.model.User;
import com.lxj.springboot.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(
            @PathVariable(name = "action") String action,
            Model model,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            HttpServletRequest request
           ) {

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if(user == null){
            return "redirect:/";
        }


        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PaginationDTO paginationList = questionService.newList(user.getId(), page, size);
        model.addAttribute("pagination",paginationList);
        return "profile";
    }
}
