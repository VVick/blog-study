package com.example.blogstudy.service;

import com.example.blogstudy.entity.BlogComment;
import com.example.blogstudy.util.PageQueryUtil;
import com.example.blogstudy.util.PageResult;

public interface CommentService {
    PageResult getBlogCommentPage(PageQueryUtil pageUtil);

    Boolean checkDone(Integer[] ids);

    Boolean reply(Long commentId,String replyBody);

    Boolean deleteBlog(Integer[] ids);

    int getCommentNumber();
}
