package com.example.blogstudy.controller.admin;

import com.example.blogstudy.service.TagService;
import com.example.blogstudy.service.impl.TagServiceImpl;
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
 * @ClassName TagController
 * @Description TODO
 * @Author OuYangCong
 * @Date 2022/9/13
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    //跳转到标签界面
    @GetMapping("/tags")
    public String List(HttpServletRequest request){
        request.setAttribute("path","tags");
        return "admin/tag";
    }

    //显示评论列表
    @GetMapping("/tags/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        //分页查询参数
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(tagService.getBlogTagPage(pageUtil));
    }

    @PostMapping(value = "/tags/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if(ids.length<1){
            return ResultGenerator.genFailResult("参数异常");
        }
        if(tagService.deleteTag(ids)){
            return ResultGenerator.genFailResult("删除成功");
        }else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }

    @PostMapping(value = "/tags/save")
    @ResponseBody
    public Result save(@RequestParam("tagName") String tagName){
        if(StringUtils.isEmpty(tagName)){
            return ResultGenerator.genFailResult("参数异常");
        }
        if(tagService.saveTag(tagName)){
            return ResultGenerator.genFailResult("保存成功");
        }else {
            return ResultGenerator.genFailResult("标签名称重复");
        }
    }
}
