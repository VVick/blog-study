package com.example.blogstudy.service;

import com.example.blogstudy.entity.BlogLink;
import com.example.blogstudy.util.PageQueryUtil;
import com.example.blogstudy.util.PageResult;

public interface LinkService {
    PageResult getBlogLinkPage(PageQueryUtil pageUtil);

    Boolean saveLink(BlogLink blogLink);

    Boolean deleteLink(Integer[] ids);

    BlogLink selectById(Integer id);

    Boolean updateLink(BlogLink blogLink);

    int getLinkNumber();
}
