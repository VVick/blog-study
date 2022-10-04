package com.example.blogstudy.service.impl;

import com.example.blogstudy.dao.BlogTagMapper;
import com.example.blogstudy.entity.BlogComment;
import com.example.blogstudy.entity.BlogTag;
import com.example.blogstudy.service.TagService;
import com.example.blogstudy.util.PageQueryUtil;
import com.example.blogstudy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName TagServiceImpl
 * @Description TODO
 * @Author OuYangCong
 * @Date 2022/9/13
 * @Version 1.0
 **/
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
        //总评论
        List<BlogComment> tags=blogTagMapper.findTagList(pageUtil);
        //总评论数量
        int total=blogTagMapper.getTotalTags(pageUtil);

        PageResult pageResult=new PageResult(tags,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean deleteTag(Integer[] ids) {
        return blogTagMapper.deleteTag(ids)>0;
    }

    @Override
    public Boolean saveTag(String tagName) {
        BlogTag blogTag=blogTagMapper.selectByTagName(tagName);
        if (blogTag==null){
            blogTag=new BlogTag();
            blogTag.setTagName(tagName);
            blogTag.setCreateTime(new Date());
            blogTag.setIsDeleted((byte) 0);
            blogTagMapper.insertSelective(blogTag);
            return true;
        }
        return false;
    }

    @Override
    public int getTagNumber() {
        return blogTagMapper.getTotalTagNumber();
    }
}
