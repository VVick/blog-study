package com.example.blogstudy.controller.admin;

import com.example.blogstudy.entity.AdminUser;
import com.example.blogstudy.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link http://13blog.site
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminUserService adminUserService;
    @Resource
    private BlogService blogService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private CommentService commentService;
    @Resource
    private LinkService linkService;
    @Resource
    private TagService tagService;



    //登录页面
    @GetMapping({"/login"})
    public String login() {
        return "admin/login";
    }

    //提交登录
    @PostMapping(value = "/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session) {
        //空验证码
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");//设置错误信息
            return "admin/login";
        }
        //空账户名与密码
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        //判断验证码
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }
        //判断用户名与密码
        AdminUser adminUser = adminUserService.login(userName, password);
        if (adminUser != null) {
            //登录成功
            session.setAttribute("loginUser", adminUser.getNickName());//设置用户名
            session.setAttribute("loginUserId", adminUser.getAdminUserId());//设置用户id
            //session过期时间设置为7200秒 即两小时
            //session.setMaxInactiveInterval(60 * 60 * 2);
            //redirect相当于再发一个get请求
            return "redirect:/admin/index";
        } else {
            //登录失败
            session.setAttribute("errorMsg", "用户名或密码错误");
            return "admin/login";
        }
    }


    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        request.setAttribute("blogCount",blogService.getBlogNumber());
        request.setAttribute("commentCount",commentService.getCommentNumber());
        request.setAttribute("categoryCount",categoryService.getCategoryNumber());
        request.setAttribute("tagCount",tagService.getTagNumber());
        request.setAttribute("linkCount",linkService.getLinkNumber());
        return "admin/index";
    }

    //退出登录
    @GetMapping({"/logout"})
    public String logout(HttpServletRequest request){
        //清空设置的数据
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }

    //修改页面
    @GetMapping({"/profile"})
    public String profile(HttpServletRequest request){
        Integer loginUserId=(int) request.getSession().getAttribute("loginUserId");
        AdminUser adminUser=adminUserService.getUserDetailById(loginUserId);
        if(adminUser==null){
            return "admin/login";
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName",adminUser.getLoginUserName());
        request.setAttribute("nickName",adminUser.getNickName());
        return "admin/profile";
    }

    //修改昵称
    @PostMapping({"/profile/name"})
    @ResponseBody
    public String nameUpdate(HttpServletRequest request,
                             @RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName){
        if(StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)){
            return "参数不能为空";
        }
        Integer loginUserId=(int) request.getSession().getAttribute("loginUserId");
        if(adminUserService.updateName(loginUserId,loginUserName,nickName)){
            return "success";
        }else {
            return "修改失败";
        }
    }

    @PostMapping({"/profile/password"})
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request,
                                 @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword){
        if(StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)){
            return "参数不能为空";
        }
        Integer loginUserId=(int) request.getSession().getAttribute("loginUserId");
        if(adminUserService.updatePassword(loginUserId,originalPassword,newPassword)){
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return "success";
        }else {
            return "修改失败";
        }

    }
}
