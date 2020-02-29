package com.lxj.springboot.springboot.mapper;

import com.lxj.springboot.springboot.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}