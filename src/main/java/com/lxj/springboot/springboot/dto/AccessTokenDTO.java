package com.lxj.springboot.springboot.dto;

import lombok.Data;

@Data
public class AccessTokenDTO {

    private String client_id;
    private String redirect_uri;
    private String client_secret;
    private String code;
    private String state;

}
