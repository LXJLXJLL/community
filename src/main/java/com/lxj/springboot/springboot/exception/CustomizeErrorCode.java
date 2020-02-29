package com.lxj.springboot.springboot.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    //question not found
    QUESTION_NOT_FOUND(2001, "你找的问题不存在,要不要换一个试试?"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NO_LOGIN(2003, "请登录后进行评论!"),
    SYS_ERROR(2004, "服务器被炸了,你稍后再试试!"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "你回复的评论不存在了,要不换一个试一哈?"),
    CONTENT_IS_EMPTY(2007, "输入内容不能为空!" +
            ""),
    ;

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

}
