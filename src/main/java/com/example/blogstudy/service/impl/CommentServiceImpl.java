package com.example.blogstudy.service.impl;

import com.example.blogstudy.dao.BlogCommentMapper;
import com.example.blogstudy.entity.BlogComment;
import com.example.blogstudy.entity.BlogLink;
import com.example.blogstudy.service.CommentService;
import com.example.blogstudy.util.PageQueryUtil;
import com.example.blogstudy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Description TODO
 * @Author OuYangCong
 * @Date 2022/9/11
 * @Version 1.0
 **/
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Override
    public PageResult getBlogCommentPage(PageQueryUtil pageUtil) {
        //总评论
        List<BlogComment> links=blogCommentMapper.findCommentList(pageUtil);
        //总评论数量
        int total=blogCommentMapper.getTotalComments(pageUtil);

        PageResult pageResult=new PageResult(links,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean checkDone(Integer[] ids) {

        return blogCommentMapper.checkDone(ids)>0;
    }

    @Override
    public Boolean reply(Long commentId, String replyBody) {
        BlogComment blogComment=blogCommentMapper.selectByPrimaryKey(commentId);
        if(blogComment!=null && blogComment.getCommentStatus().intValue()==1){
            blogComment.setReplyBody(replyBody);
            blogComment.setCommentCreateTime(new Date());
            return blogCommentMapper.updateByPrimaryKeySelective(blogComment)>0;
        }
        return false;
    }

    @Override
    public Boolean deleteBlog(Integer[] ids) {


        return blogCommentMapper.deleteBatch(ids)>0;
    }

    @Override
    public int getCommentNumber() {
        return blogCommentMapper.getCommentNumber();
    }
}
