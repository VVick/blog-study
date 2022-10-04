package com.example.blogstudy.service.impl;

import com.example.blogstudy.dao.BlogCategoryMapper;
import com.example.blogstudy.entity.BlogCategory;
import com.example.blogstudy.entity.BlogComment;
import com.example.blogstudy.service.CategoryService;
import com.example.blogstudy.util.PageQueryUtil;
import com.example.blogstudy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author OuYangCong
 * @Date 2022/9/14
 * @Version 1.0
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil pageUtil) {
        //总评论
        List<BlogCategory> links=blogCategoryMapper.findCategoryList(pageUtil);
        //总评论数量
        int total=blogCategoryMapper.getTotalCategories(pageUtil);

        PageResult pageResult=new PageResult(links,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean saveCategory(String categoryName, String categoryIcon) {
        BlogCategory temp = blogCategoryMapper.selectByCategoryName(categoryName);
        if (temp == null) {
            BlogCategory blogCategory = new BlogCategory();
            blogCategory.setCategoryName(categoryName);
            blogCategory.setCategoryIcon(categoryIcon);
            return blogCategoryMapper.insertSelective(blogCategory) > 0;
        }
        return false;
    }

    @Override
    public Boolean deleteCategory(Integer[] ids) {
        return blogCategoryMapper.deleteCategories(ids)>0;
    }

    @Override
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        BlogCategory temp = blogCategoryMapper.selectByCategoryName(categoryName);
        if (temp == null) {
            BlogCategory blogCategory = new BlogCategory();
            blogCategory.setCategoryName(categoryName);
            blogCategory.setCategoryIcon(categoryIcon);
            blogCategory.setCategoryId(categoryId);
            return blogCategoryMapper.updateByPrimaryKeySelective(blogCategory)> 0;
        }
        return false;
    }

    @Override
    public List<BlogCategory> getAllCategories() {
        return blogCategoryMapper.findCategoryList(null);
    }

    @Override
    public int getCategoryNumber() {
        return blogCategoryMapper.getCategoryNumber();
    }
}
