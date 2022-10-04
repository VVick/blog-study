package com.example.blogstudy.service;

import com.example.blogstudy.entity.BlogConfig;

import java.util.List;
import java.util.Map;

public interface ConfigService {

    List<BlogConfig> getTotalConfig();

    Map<String,String> getAllConfigs();

    boolean saveWebsite(Map map);


    boolean saveUserInfo(Map map);

    boolean saveFooter(Map map);
}
