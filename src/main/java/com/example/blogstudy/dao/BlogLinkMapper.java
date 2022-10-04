package com.example.blogstudy.dao;

import com.example.blogstudy.entity.BlogLink;
import com.example.blogstudy.util.PageQueryUtil;

import java.util.List;

public interface BlogLinkMapper {
    //根据id删除link
    int deleteByPrimaryKey(Integer linkId);

    //插入link
    int insert(BlogLink record);

    int insertSelective(BlogLink record);

    //根据id选择link
    BlogLink selectByPrimaryKey(Integer linkId);

    //更新link
    int updateByPrimaryKeySelective(BlogLink record);

    int updateByPrimaryKey(BlogLink record);

    //查询指定link表
    List<BlogLink> findLinkList(PageQueryUtil pageQueryUtil);

    //查询总link数量
    int getTotalLinks(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);

    int getTotalLinkNumber();
}
