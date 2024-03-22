package com.blog.blogapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.blogapp.model.Blog;

@Service
public interface Blogservice {
   void addBlog(Blog blog);

   List<Blog> getBlogs();

   Blog getBlogs(int id);

void deleteBlog(int id);

void updateBlog(Blog blog); 

}
