package com.example.blogstudy.service;

import com.example.blogstudy.entity.BlogTag;
import com.example.blogstudy.util.PageQueryUtil;
import com.example.blogstudy.util.PageResult;

public interface TagService {
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    Boolean deleteTag(Integer[] ids);

    Boolean saveTag(String tagName);

    int getTagNumber();
}
