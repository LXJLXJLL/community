package com.lxj.springboot.springboot.mapper;

import com.lxj.springboot.springboot.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);
}