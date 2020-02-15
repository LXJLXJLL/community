package com.lxj.springboot.springboot.service;

import com.lxj.springboot.springboot.dto.PaginationDTO;
import com.lxj.springboot.springboot.dto.QuestionDTO;
import com.lxj.springboot.springboot.mapper.QuestionMapper;
import com.lxj.springboot.springboot.mapper.UserMapper;
import com.lxj.springboot.springboot.model.Question;
import com.lxj.springboot.springboot.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;

        Integer totalCount = questionMapper.count();

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //如果页码小于1就为1
        if (page < 1) {
            page = 1;
        }
        //大于最大页码的时候就设置为最大页码
        if (page > totalPage) {
            page = totalPage;
        }

        //总数
        paginationDTO.setPagination(totalPage, page);

        //分页 offset 偏移量 limit(偏移量,返回的数)
        //size*(page-1)
        Integer offset = size * (page - 1);
        //每一页的列表
        List<Question> list = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        for (Question question : list) {
            User user = userMapper.findById(question.getCreateId());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    /**
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO newList(Integer userId, Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //如果页码小于1就为1
        if (page < 1) {
            page = 1;
        }
        //大于最大页码的时候就设置为最大页码
        if (page > totalPage) {
            page = totalPage;
        }

        //总数
        paginationDTO.setPagination(totalPage, page);

        //size*(page-1)
        Integer offset = size * (page - 1);
        List<Question> list = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        for (Question question : list) {
            User user = userMapper.findById(question.getCreateId());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;

    }
}
