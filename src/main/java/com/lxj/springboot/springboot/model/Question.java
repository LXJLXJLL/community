package com.lxj.springboot.springboot.model;

import lombok.Data;

@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer createId;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

}
