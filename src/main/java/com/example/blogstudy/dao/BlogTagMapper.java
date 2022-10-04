package com.example.blogstudy.dao;

import com.example.blogstudy.entity.BlogComment;
import com.example.blogstudy.entity.BlogTag;
import com.example.blogstudy.util.PageQueryUtil;

import java.util.List;

public interface BlogTagMapper {
    //返回tag列表
    List<BlogComment> findTagList(PageQueryUtil pageQueryUtil);

    //查询总tag数量
    int getTotalTags(PageQueryUtil pageUtil);

    int deleteTag(Integer[] ids);

    int insertSelective(BlogTag record);

    BlogTag selectByTagName(String tagName);

    int batchInsertBlogTag(List<BlogTag> tagList);

    int getTotalTagNumber();

}
