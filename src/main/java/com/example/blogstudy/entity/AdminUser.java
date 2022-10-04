package com.example.blogstudy.entity;

/**
 * @ClassName AdminUser
 * @Description 管理员实体层
 * @Author OuYangCong
 * @Date 2022/8/30
 * @Version 1.0
 **/
public class AdminUser {
    //管理员id
    private Integer adminUserId;

    //用户名
    private String loginUserName;

    //登录密码
    private String loginPassword;

    //别名
    private String nickName;

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Byte getLocked() {
        return locked;
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    private Byte locked;
}
