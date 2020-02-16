package com.lxj.springboot.springboot.service;

import com.lxj.springboot.springboot.dto.PaginationDTO;
import com.lxj.springboot.springboot.dto.QuestionDTO;
import com.lxj.springboot.springboot.mapper.QuestionMapper;
import com.lxj.springboot.springboot.mapper.UserMapper;
import com.lxj.springboot.springboot.model.Question;
import com.lxj.springboot.springboot.model.QuestionExample;
import com.lxj.springboot.springboot.model.User;
import org.apache.ibatis.session.RowBounds;
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

        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());

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
        if (page > totalPage && totalPage != 0) {
            page = totalPage;
        }

        //总数
        paginationDTO.setPagination(totalPage, page);

        //分页 offset 偏移量 limit(偏移量,返回的数)
        //size*(page-1)
        Integer offset = size * (page - 1);
        //每一页的列表
        List<Question> list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        for (Question question : list) {
            User user = userMapper.selectByPrimaryKey(question.getcreateId());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    /**
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO newList(Integer userId, Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        QuestionExample example = new QuestionExample();
        example.createCriteria().andcreateIdEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(example);

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
        if (page > totalPage && totalPage != 0) {
            page = totalPage;
        }

        //总数
        paginationDTO.setPagination(totalPage, page);

        //size*(page-1)
        Integer offset = size * (page - 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andcreateIdEqualTo(userId);
        List<Question> list = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        for (Question question : list) {
            User user = userMapper.selectByPrimaryKey(question.getcreateId());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;

    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getcreateId());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updateQuestion, example);
        }

    }
}
