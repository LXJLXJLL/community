package com.lxj.springboot.springboot.service;

import com.lxj.springboot.springboot.enums.CommentTyppeEnum;
import com.lxj.springboot.springboot.exception.CustomizeErrorCode;
import com.lxj.springboot.springboot.exception.CustomizeException;
import com.lxj.springboot.springboot.mapper.CommentMapper;
import com.lxj.springboot.springboot.mapper.QuestionExtMapper;
import com.lxj.springboot.springboot.mapper.QuestionMapper;
import com.lxj.springboot.springboot.model.Comment;
import com.lxj.springboot.springboot.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public void insert(Comment comment) {
       if(comment == null || comment.getParentId() == 0){
           throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
       }

       if(comment.getType() == null || !CommentTyppeEnum.isExist(comment.getType())){
           throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
       }

       if(comment.getType().equals(CommentTyppeEnum.COMMENT.getType())){
            //回复评论
           Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
           if(dbComment == null){
               throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
           }
           commentMapper.insert(comment);
       }else{
           //回复问题
           Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
           if(question == null){
               throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
           }
           commentMapper.insert(comment);
           question.setCommentCount(1);
           questionExtMapper.incCommentCount(question);
       }
    }
}
