package com.lxj.springboot.springboot.dto;

import com.lxj.springboot.springboot.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    //标题
    private String title;
    //描述
    private String description;
    //标签
    private String tag;
    //格林时间
    private Long gmtCreate;
    //格林时间
    private Long gmtModified;
    //创建人
    private Long creator;
    //浏览数
    private Integer viewCount;
    //回复数
    private Integer commentCount;
    //关注数
    private Integer likeCount;
    //主表关联
    private User user;
}
