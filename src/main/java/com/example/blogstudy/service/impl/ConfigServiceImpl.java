package com.example.blogstudy.service.impl;

import com.example.blogstudy.dao.BlogConfigMapper;
import com.example.blogstudy.entity.BlogConfig;
import com.example.blogstudy.service.ConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName ConfigServiceImpl
 * @Description TODO
 * @Author OuYangCong
 * @Date 2022/10/3
 * @Version 1.0
 **/

@Service
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private BlogConfigMapper blogConfigMapper;

//    public static final String websiteName = "personal blog";
//    public static final String websiteDescription = "personal blog是SpringBoot2+Thymeleaf+Mybatis建造的个人博客网站.SpringBoot实战博客源码.个人博客搭建";
//    public static final String websiteLogo = "/admin/dist/img/logo2.png";
//    public static final String websiteIcon = "/admin/dist/img/favicon.png";
//
//    public static final String yourAvatar = "/admin/dist/img/13.png";
//    public static final String yourEmail = "2449207463@qq.com";
//    public static final String yourName = "十三";
//
//    public static final String footerAbout = "your personal blog. have fun.";
//    public static final String footerICP = "浙ICP备 xxxxxx-x号";
//    public static final String footerCopyRight = "@2018 十三";
//    public static final String footerPoweredBy = "personal blog";
//    public static final String footerPoweredByURL = "##";

    @Override
    public List<BlogConfig> getTotalConfig() {
        return blogConfigMapper.getTotalConfig();
    }

    @Override
    public Map<String, String> getAllConfigs() {
        //获取所有的map并封装为list
        List<BlogConfig> blogConfigs = blogConfigMapper.getTotalConfig();
        //list转换为map
        Map<String, String> configMap = blogConfigs.stream().collect(Collectors.toMap(BlogConfig::getConfigName, BlogConfig::getConfigValue));
        //遍历map
//        for (Map.Entry<String, String> config : configMap.entrySet()) {
//            if ("websiteName".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(websiteName);
//            }
//            if ("websiteDescription".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(websiteDescription);
//            }
//            if ("websiteLogo".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(websiteLogo);
//            }
//            if ("websiteIcon".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(websiteIcon);
//            }
//            if ("yourAvatar".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(yourAvatar);
//            }
//            if ("yourEmail".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(yourEmail);
//            }
//            if ("yourName".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(yourName);
//            }
//            if ("footerAbout".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(footerAbout);
//            }
//            if ("footerICP".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(footerICP);
//            }
//            if ("footerCopyRight".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(footerCopyRight);
//            }
//            if ("footerPoweredBy".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(footerPoweredBy);
//            }
//            if ("footerPoweredByURL".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(footerPoweredByURL);
//            }
//        }
        return configMap;
    }

    @Override
    public boolean saveWebsite(Map map) {

        map.forEach((key, value) -> {
            BlogConfig temp = new BlogConfig();
            temp.setConfigName((String) key);
            temp.setConfigValue((String) value);
            temp.setUpdateTime(new Date());
            blogConfigMapper.updateBlogConfigByPrimaryKeySelective(temp);
        });
        return true;
    }

    @Override
    public boolean saveUserInfo(Map map) {
        map.forEach((key, value) -> {
            BlogConfig temp = new BlogConfig();
            temp.setConfigName((String) key);
            temp.setConfigValue((String) value);
            temp.setUpdateTime(new Date());
            blogConfigMapper.updateBlogConfigByPrimaryKeySelective(temp);
        });
        return true;
    }

    @Override
    public boolean saveFooter(Map map) {
        map.forEach((key, value) -> {
            BlogConfig temp = new BlogConfig();
            temp.setConfigName((String) key);
            temp.setConfigValue((String) value);
            temp.setUpdateTime(new Date());
            blogConfigMapper.updateBlogConfigByPrimaryKeySelective(temp);
        });
        return true;
    }
}
