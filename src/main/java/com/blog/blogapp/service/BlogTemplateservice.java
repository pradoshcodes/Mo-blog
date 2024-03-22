package com.blog.blogapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Service;

import com.blog.blogapp.model.Blog;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class BlogTemplateservice implements Blogservice {
    private final JdbcTemplate jdbcTemplate;

    @Value("${prefix}")
    private String prefix;

    @Override
    public void addBlog(Blog blog) {

        var heading = prefix+" " + blog.getHeading();
        String sql = "INSERT INTO moblog(heading,description) VALUES (?,?)";
        jdbcTemplate.update(sql,heading, blog.getDescription());
    }

    @Override
    public List<Blog> getBlogs() {
        String sql = "SELECT  * FROM moblog";
        RowMapper<Blog> rowMapper = (resultSet, rowNum) -> new Blog(
                resultSet.getInt("id"),
                resultSet.getString("heading"),
                resultSet.getString("description"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Blog getBlogs(int id) {
        String sql = "SELECT  * FROM moblog WHERE id=?";

        RowMapper<Blog> rowMapper = (resultSet, rowNum) -> new Blog(
                resultSet.getInt("id"),
                resultSet.getString("heading"),
                resultSet.getString("description"));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public void deleteBlog(int id) {
        String sql = "DELETE FROM moblog WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateBlog(Blog blog) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBlog'");
    }


  
}
