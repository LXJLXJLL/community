package com.lxj.springboot.springboot.enums;

public enum CommentTyppeEnum {
    //问题类型
    QUESTION(1),
    //评论类型
    COMMENT(2);
    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTyppeEnum value : CommentTyppeEnum.values()) {
            if (value.getType().equals(type)) {
                return true;
            }
        }
        return false;

    }

    public Integer getType() {
        return type;
    }

    CommentTyppeEnum(Integer type) {
        this.type = type;
    }
}
