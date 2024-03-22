package com.blog.blogapp.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.blogapp.model.Blog;
import com.blog.blogapp.service.Blogservice;

@Controller
public class BlogController {
    private final Blogservice blogservice;
    


    public BlogController(@Qualifier("blogserviceclient") Blogservice blogservice) {
        this.blogservice = blogservice;
    }

    @GetMapping({ "/", "/blogs" })
    public String blogs(Model model) {
        var blogs = blogservice.getBlogs();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }

    @GetMapping("/blog")
    public String blog(@RequestParam int id, Model model) {
        var blog = blogservice.getBlogs(id);
        model.addAttribute("blog", blog);
        return "blog";
    }

    @GetMapping("/add-blog")
    public String addBlog(Model model) {
        model.addAttribute("blog", new Blog());
        return "add-blog";
    }

    @PostMapping("/add-blog")
    public String addBlog(@ModelAttribute Blog blog) {
        if (blog.getId()==0) 
            
        blogservice.addBlog(blog);
        else

            blogservice.updateBlog(blog);
        return "redirect:/blogs";
       
    }

    @GetMapping("/delete-blog")
    public String deleteBlog(@RequestParam("id") int id) {
        blogservice.deleteBlog(id);
        return "redirect:/blogs";
    }

    @GetMapping("/update-blog")
    public String updateBlog(@RequestParam("id") int id, Model model) {
        var  blog = blogservice.getBlogs(id);
        model.addAttribute("blog", blog);
        
        return "add-blog";
    }
}