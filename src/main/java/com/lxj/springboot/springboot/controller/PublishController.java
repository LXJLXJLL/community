package com.lxj.springboot.springboot.controller;

import com.lxj.springboot.springboot.mapper.QuestionMapper;
import com.lxj.springboot.springboot.mapper.UserMapper;
import com.lxj.springboot.springboot.model.Question;
import com.lxj.springboot.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || "".equals(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || "".equals(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        //获取cookie里的用户信息
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        System.out.println(user);
                    }
                    break;
                }
            }

            if (user == null) {
                model.addAttribute("error", "用户未登录");
                return "publish";
            }

            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreateId(Integer.valueOf(user.getAccountId()));
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        } else {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        return "redirect:/";
    }
}
