package com.example.blogstudy.service;

import com.example.blogstudy.entity.BlogCategory;
import com.example.blogstudy.util.PageQueryUtil;
import com.example.blogstudy.util.PageResult;

import java.util.List;

public interface CategoryService {

    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

    Boolean saveCategory(String categoryName,String categoryIcon);

    Boolean deleteCategory(Integer[] ids);

    Boolean updateCategory(Integer categoryId,String categoryName,String categoryIcon);

    List<BlogCategory> getAllCategories();

    int getCategoryNumber();
}
