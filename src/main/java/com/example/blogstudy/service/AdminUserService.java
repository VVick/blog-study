package com.example.blogstudy.service;

import com.example.blogstudy.entity.AdminUser;

public interface AdminUserService {

    //登录
    AdminUser login(String userName, String password);

    //修改姓名
    Boolean updateName(Integer loginUserId,String loginUserName,String nickName);

    Boolean updatePassword(Integer loginUserId,String originalPassword,String newPassword);

    //根据id获取用户信息
    AdminUser getUserDetailById(Integer loginUserId);

}
