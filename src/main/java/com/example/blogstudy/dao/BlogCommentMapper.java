package com.example.blogstudy.dao;

import com.example.blogstudy.entity.BlogComment;
import com.example.blogstudy.entity.BlogLink;
import com.example.blogstudy.util.PageQueryUtil;

import java.util.List;

public interface BlogCommentMapper {
    //返回link列表
    List<BlogComment> findCommentList(PageQueryUtil pageQueryUtil);

    //查询总link数量
    int getTotalComments(PageQueryUtil pageUtil);

    int checkDone(Integer[] ids);

    BlogComment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(BlogComment record);

    int deleteBatch(Integer[] ids);

    int getCommentNumber();

}
