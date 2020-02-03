package com.lxj.springboot.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexContrlller {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
