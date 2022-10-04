package com.example.blogstudy.service;

import com.example.blogstudy.entity.Blog;
import com.example.blogstudy.util.PageQueryUtil;
import com.example.blogstudy.util.PageResult;

public interface BlogService {
    public PageResult getBlogPage(PageQueryUtil pageUtil);

    String saveBlog(Blog blog);

    Blog getBlogById(Long blogId);

    String updateBlog(Blog blog);

    Boolean deleteBatch(Integer[] ids);

    int getBlogNumber();

}
