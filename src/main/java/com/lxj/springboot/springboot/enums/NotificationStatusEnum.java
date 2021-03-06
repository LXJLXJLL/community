package com.lxj.springboot.springboot.enums;

/**
 * Created by codedrinker on 2019/6/14.
 */
public enum NotificationStatusEnum {
    //未读0,已读1
    UNREAD(0), READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
