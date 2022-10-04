package com.example.blogstudy.service.impl;

import com.example.blogstudy.dao.BlogLinkMapper;
import com.example.blogstudy.entity.BlogLink;
import com.example.blogstudy.service.LinkService;
import com.example.blogstudy.util.PageQueryUtil;
import com.example.blogstudy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName LinkServiceImpl
 * @Description TODO
 * @Author OuYangCong
 * @Date 2022/9/11
 * @Version 1.0
 **/
@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private BlogLinkMapper blogLinkMapper;

    //根据查询参数返回查询结果
    @Override
    public PageResult getBlogLinkPage(PageQueryUtil pageUtil) {

        //总页面
        List<BlogLink> links=blogLinkMapper.findLinkList(pageUtil);
        //总link数量
        int total=blogLinkMapper.getTotalLinks(pageUtil);

        PageResult pageResult=new PageResult(links,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    //保存
    @Override
    public Boolean saveLink(BlogLink blogLink) {
        return blogLinkMapper.insertSelective(blogLink)>0;
    }

    //删除
    @Override
    public Boolean deleteLink(Integer[] ids) {
        if(blogLinkMapper.deleteBatch(ids) >0){
            return true;
        }else {
            return  false;
        }
    }

    //根据id查找
    @Override
    public BlogLink selectById(Integer id) {

        return blogLinkMapper.selectByPrimaryKey(id);
    }

    //更新link
    @Override
    public Boolean updateLink(BlogLink blogLink) {
        return blogLinkMapper.updateByPrimaryKeySelective(blogLink)>0;
    }

    @Override
    public int getLinkNumber() {
        return blogLinkMapper.getTotalLinkNumber();
    }
}
