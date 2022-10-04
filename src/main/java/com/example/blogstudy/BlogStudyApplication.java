package com.example.blogstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.example.blogstudy.dao")
@SpringBootApplication
public class BlogStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogStudyApplication.class, args);
    }

}
