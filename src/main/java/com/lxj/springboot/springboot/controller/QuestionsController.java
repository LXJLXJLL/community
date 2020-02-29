package com.lxj.springboot.springboot.controller;

import com.lxj.springboot.springboot.dto.CommentDTO;
import com.lxj.springboot.springboot.dto.QuestionDTO;
import com.lxj.springboot.springboot.enums.CommentTyppeEnum;
import com.lxj.springboot.springboot.service.CommentService;
import com.lxj.springboot.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionsController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {
        QuestionDTO questionDTO = questionService.getById(id);

       List<CommentDTO> comments =  commentService.listByTargetId(id, CommentTyppeEnum.QUESTION);

        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        return "question";
    }
}
