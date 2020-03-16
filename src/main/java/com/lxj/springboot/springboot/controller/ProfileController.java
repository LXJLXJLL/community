package com.lxj.springboot.springboot.controller;

import com.lxj.springboot.springboot.dto.PaginationDTO;
import com.lxj.springboot.springboot.model.User;
import com.lxj.springboot.springboot.service.NotificationService;
import com.lxj.springboot.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lxj
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(
            @PathVariable(name = "action") String action,
            Model model,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            HttpServletRequest request
    ) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            PaginationDTO paginationList = questionService.newList(Long.valueOf(user.getAccountId()), page, size);
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
            model.addAttribute("pagination", paginationList);
        } else if ("replies".equals(action)) {
            PaginationDTO paginationList = notificationService.list(Long.valueOf(user.getAccountId()), page, size);
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            model.addAttribute("pagination", paginationList);
        }

        return "profile";
    }
}
