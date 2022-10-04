package com.example.blogstudy.dao;

import com.example.blogstudy.entity.BlogConfig;


import java.util.List;

public interface BlogConfigMapper {

    List<BlogConfig> getTotalConfig();

    int updateBlogConfigByPrimaryKeySelective(BlogConfig blogConfig);
}
