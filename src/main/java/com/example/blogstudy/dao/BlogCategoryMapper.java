package com.example.blogstudy.dao;

import com.example.blogstudy.entity.BlogCategory;
import com.example.blogstudy.entity.BlogComment;
import com.example.blogstudy.entity.BlogLink;
import com.example.blogstudy.util.PageQueryUtil;

import java.util.List;

public interface BlogCategoryMapper {

    //返回link列表
    List<BlogCategory> findCategoryList(PageQueryUtil pageQueryUtil);

    //查询总link数量
    int getTotalCategories(PageQueryUtil pageUtil);

    int insertSelective(BlogCategory record);

    BlogCategory selectByCategoryName(String categoryName);

    int deleteCategories(Integer[] ids);

    int updateByPrimaryKeySelective(BlogCategory record);

    BlogCategory selectByPrimaryKey(Integer categoryId);

    int getCategoryNumber();

}
