package com.example.blogstudy.service.impl;


import com.example.blogstudy.dao.AdminUserMapper;
import com.example.blogstudy.entity.AdminUser;
import com.example.blogstudy.service.AdminUserService;
import com.example.blogstudy.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        //密码md5加密之后再存到数据库中
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return adminUserMapper.login(userName, passwordMd5);
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser=adminUserMapper.selectByPrimaryKey(loginUserId);
        if(adminUser != null){
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if(adminUserMapper.updateByPrimaryKeySelective(adminUser)>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser=adminUserMapper.selectByPrimaryKey(loginUserId);
        //判断用户非空
        if(adminUser!=null){
            String originalPasswordMd5=MD5Util.MD5Encode(originalPassword,"UTf-8");
            String newPasswordMd5=MD5Util.MD5Encode(newPassword,"UTF-8");
            //判断输入的原密码是否与原密码相等
            if(originalPasswordMd5.equals(adminUser.getLoginPassword())){
                //相等则修改成新密码
                adminUser.setLoginPassword(newPasswordMd5);
                if(adminUserMapper.updateByPrimaryKeySelective(adminUser)>0){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {

        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }
}
