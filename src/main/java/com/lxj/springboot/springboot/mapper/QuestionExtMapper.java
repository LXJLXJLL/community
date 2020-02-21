package com.lxj.springboot.springboot.mapper;

import com.lxj.springboot.springboot.model.Question;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}