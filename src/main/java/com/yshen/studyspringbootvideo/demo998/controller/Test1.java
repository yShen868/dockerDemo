package com.yshen.studyspringbootvideo.demo998.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郑悦恺
 * @Classname Test1
 * @Description TODO
 * @Date 2020/2/28 17:32
 */


@RestController
public class Test1 {

    @RequestMapping("/test1")
    public String testboot(){
        return "hahahHAHAHA哈哈哈";
    }









}
