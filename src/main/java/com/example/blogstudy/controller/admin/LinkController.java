package com.example.blogstudy.controller.admin;

import com.example.blogstudy.entity.BlogLink;
import com.example.blogstudy.service.LinkService;
import com.example.blogstudy.util.PageQueryUtil;
import com.example.blogstudy.util.Result;
import com.example.blogstudy.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName LinkController
 * @Description TODO
 * @Author OuYangCong
 * @Date 2022/9/11
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class LinkController {
    @Autowired
    private LinkService linkService;

    //跳转列表界面
    @GetMapping("/links")
    public String linkPage(HttpServletRequest request){
        request.setAttribute("path","links");
        return "admin/link";
    }

    //显示列表
    @GetMapping("/links/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        //先判断有没有分页参数
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        //生成分页查询工具，分页查询参数
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(linkService.getBlogLinkPage(pageUtil));
    }

    //新增
    @PostMapping(value = "/links/save")
    @ResponseBody
    public Result save(@RequestParam("linkType") Integer linkType,
                       @RequestParam("linkName") String linkName,
                       @RequestParam("linkUrl") String linkUrl,
                       @RequestParam("linkRank") Integer linkRank,
                       @RequestParam("linkDescription") String linkDescription){
        if(linkType==null||linkType<0||linkRank==null||linkRank<0||StringUtils.isEmpty(linkName)||StringUtils.isEmpty(linkUrl)||StringUtils.isEmpty(linkDescription)){
            return ResultGenerator.genFailResult("参数异常");
        }
        BlogLink blogLink=new BlogLink();
        blogLink.setLinkName(linkName);
        blogLink.setLinkDescription(linkDescription);
        blogLink.setLinkType(linkType.byteValue());
        blogLink.setLinkRank(linkRank);
        blogLink.setLinkUrl(linkUrl);
        return ResultGenerator.genSuccessResult(linkService.saveLink(blogLink));
    }

    //删除
    @PostMapping(value = "/links/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if(ids.length<1){
            return ResultGenerator.genFailResult("参数异常");
        }
        if(linkService.deleteLink(ids)){
            return ResultGenerator.genFailResult("删除成功");
        }else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }

    //根据id查结果
    @GetMapping(value = "/links/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id){
        BlogLink blogLink=linkService.selectById(id);
        return ResultGenerator.genSuccessResult(blogLink);
    }

    //根据id查结果
    @PostMapping(value = "/links/update")
    @ResponseBody
    private Result update(@RequestParam("linkId") Integer linkId,
                          @RequestParam("linkType") Integer linkType,
                          @RequestParam("linkName") String linkName,
                          @RequestParam("linkUrl") String linkUrl,
                          @RequestParam("linkRank") Integer linkRank,
                          @RequestParam("linkDescription") String linkDescription){
        BlogLink blogLink=linkService.selectById(linkId);
        if(blogLink==null){
            return ResultGenerator.genFailResult("无数据");
        }
        if(linkType==null||linkType<0||linkRank==null||linkRank<0||StringUtils.isEmpty(linkName)||StringUtils.isEmpty(linkUrl)||StringUtils.isEmpty(linkDescription)){
            return ResultGenerator.genFailResult("参数异常");
        }
        blogLink.setLinkName(linkName);
        blogLink.setLinkDescription(linkDescription);
        blogLink.setLinkType(linkType.byteValue());
        blogLink.setLinkRank(linkRank);
        blogLink.setLinkUrl(linkUrl);
        return ResultGenerator.genSuccessResult(linkService.updateLink(blogLink));
    }
}
