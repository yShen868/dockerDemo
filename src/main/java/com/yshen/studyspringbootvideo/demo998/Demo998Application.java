package com.yshen.studyspringbootvideo.demo998;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 郑悦恺
 */


@SpringBootApplication
//@ComponentScan(basePackages = {"com.yshen.studyspringbootvideo.demo998.config"})
//@MapperScan("com.yshen.s")
public class Demo998Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo998Application.class, args);
    }

}
