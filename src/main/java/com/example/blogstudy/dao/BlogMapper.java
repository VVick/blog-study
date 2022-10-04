package com.example.blogstudy.dao;

import com.example.blogstudy.entity.Blog;
import com.example.blogstudy.util.PageQueryUtil;

import java.util.List;

public interface BlogMapper {
    List<Blog> findBlogList(PageQueryUtil pageUtil);
    int getTotalBlogs(PageQueryUtil pageUtil);

    int insertSelective(Blog record);
    Blog selectByPrimaryKey(Long blogId);
    int updateByPrimaryKeySelective(Blog record);

    int deleteBatch(Integer[] ids);

    int getBlogNumber();
}
