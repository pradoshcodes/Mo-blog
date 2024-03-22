package com.blog.blogapp.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.blog.blogapp.model.Blog;




@Configuration
public class Blogbean {
    @Bean
    ArrayList<Blog> arrayList(){
        return new ArrayList<>();
    }
}
