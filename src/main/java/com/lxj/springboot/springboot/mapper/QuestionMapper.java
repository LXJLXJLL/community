package com.lxj.springboot.springboot.mapper;

import com.lxj.springboot.springboot.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,createId,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{createId},#{tag})")
    void create(Question question);
}
