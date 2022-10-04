package com.example.blogstudy.controller.admin;

import com.example.blogstudy.entity.BlogLink;
import com.example.blogstudy.service.ConfigService;
import com.example.blogstudy.util.Result;
import com.example.blogstudy.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ConfigController
 * @Description TODO
 * @Author OuYangCong
 * @Date 2022/10/3
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class ConfigController {
    @Resource
    private ConfigService configService;

    //跳转列表界面
    @GetMapping("/configurations")
    public String configPage(HttpServletRequest request){
        request.setAttribute("path","configurations");
        request.setAttribute("configurations", configService.getAllConfigs());
        return "admin/configuration";
    }


    //修改website相关信息
    @PostMapping(value = "/configurations/website")
    @ResponseBody
    public Result updateWebsite(@RequestParam("websiteName") String websiteName,
                                @RequestParam("websiteDescription") String websiteDescription,
                                @RequestParam("websiteLogo") String websiteLogo,
                                @RequestParam("websiteIcon") String websiteIcon){
        if (!StringUtils.isEmpty(websiteName)) {
            ResultGenerator.genFailResult("参数不能为空");
        }
        if (!StringUtils.isEmpty(websiteDescription)) {
            ResultGenerator.genFailResult("参数不能为空");
        }
        if (!StringUtils.isEmpty(websiteLogo)) {
            ResultGenerator.genFailResult("参数不能为空");
        }
        if (!StringUtils.isEmpty(websiteIcon)) {
            ResultGenerator.genFailResult("参数不能为空");
        }
        Map tempmap=new HashMap<String,String>();
        tempmap.put("websiteName",websiteName);
        tempmap.put("websiteLogo",websiteLogo);
        tempmap.put("websiteIcon",websiteIcon);
        tempmap.put("websiteDescription",websiteDescription);
        if(configService.saveWebsite(tempmap)){
            return ResultGenerator.genSuccessResult(1);
        }
        return ResultGenerator.genSuccessResult("修改失败");
    }

    //修改userInfo相关信息
    @PostMapping(value = "/configurations/userInfo")
    @ResponseBody
    public Result updateUserInfo(@RequestParam("yourAvatar") String yourAvatar,
                                @RequestParam("yourName") String yourName,
                                @RequestParam("yourEmail") String yourEmail){
        if (!StringUtils.isEmpty(yourAvatar)) {
            ResultGenerator.genFailResult("参数不能为空");
        }
        if (!StringUtils.isEmpty(yourName)) {
            ResultGenerator.genFailResult("参数不能为空");
        }
        if (!StringUtils.isEmpty(yourEmail)) {
            ResultGenerator.genFailResult("参数不能为空");
        }
        Map tempmap=new HashMap<String,String>();
        tempmap.put("yourAvatar",yourAvatar);
        tempmap.put("yourName",yourName);
        tempmap.put("yourEmail",yourEmail);
        if(configService.saveUserInfo(tempmap)){
            return ResultGenerator.genSuccessResult(1);
        }
        return ResultGenerator.genSuccessResult("修改失败");
    }

    //修改userInfo相关信息
    @PostMapping(value = "/configurations/footer")
    @ResponseBody
    public Result updateFooter(@RequestParam("footerAbout") String footerAbout,
                                 @RequestParam("footerICP") String footerICP,
                                 @RequestParam("footerCopyRight") String footerCopyRight,
                                 @RequestParam("footerPoweredBy") String footerPoweredBy,
                                 @RequestParam("footerPoweredByURL") String footerPoweredByURL){
        if (!StringUtils.isEmpty(footerAbout)) {
            ResultGenerator.genFailResult("参数不能为空");
        }
        if (!StringUtils.isEmpty(footerICP)) {
            ResultGenerator.genFailResult("参数不能为空");
        }
        if (!StringUtils.isEmpty(footerCopyRight)) {
            ResultGenerator.genFailResult("参数不能为空");
        }
        if (!StringUtils.isEmpty(footerPoweredBy)) {
            ResultGenerator.genFailResult("参数不能为空");
        }
        if (!StringUtils.isEmpty(footerPoweredByURL)) {
            ResultGenerator.genFailResult("参数不能为空");
        }
        Map tempmap=new HashMap<String,String>();
        tempmap.put("footerAbout",footerAbout);
        tempmap.put("footerICP",footerICP);
        tempmap.put("footerCopyRight",footerCopyRight);
        tempmap.put("footerPoweredBy",footerPoweredBy);
        tempmap.put("footerPoweredByURL",footerPoweredByURL);
        if(configService.saveFooter(tempmap)){
            return ResultGenerator.genSuccessResult(1);
        }
        return ResultGenerator.genSuccessResult("修改失败");
    }
}
