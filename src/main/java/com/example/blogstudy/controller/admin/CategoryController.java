package com.example.blogstudy.controller.admin;

import com.example.blogstudy.service.CategoryService;
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
 * @ClassName CategoryController
 * @Description TODO
 * @Author OuYangCong
 * @Date 2022/9/14
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //跳转到分类界面
    @GetMapping("/categories")
    public String List(HttpServletRequest request){
        request.setAttribute("path","categories");
        return "admin/category";
    }

    //显示分类列表
    @GetMapping("/categories/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        //分页查询参数
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(categoryService.getBlogCategoryPage(pageUtil));
    }

    @PostMapping("/categories/save")
    @ResponseBody
    public Result save(@RequestParam("categoryName") String categoryName,
                       @RequestParam("categoryIcon") String categoryIcon){
        if(StringUtils.isEmpty(categoryName)||StringUtils.isEmpty(categoryIcon)){
            ResultGenerator.genFailResult("参数错误");
        }
        if(categoryService.saveCategory(categoryName,categoryIcon)){
            return ResultGenerator.genSuccessResult("保存成功");
        }else {
            return ResultGenerator.genFailResult("保存错误");
        }
    }

    @PostMapping("/categories/delete")
    @ResponseBody
    public Result save(@RequestBody Integer[] ids){
        if(ids.length<1){
            return ResultGenerator.genFailResult("参数异常");
        }
        if(categoryService.deleteCategory(ids)){
            return ResultGenerator.genSuccessResult("删除成功");
        } else {
            return ResultGenerator.genFailResult("删除错误");
        }
    }

    @PostMapping("/categories/update")
    @ResponseBody
    public Result update(@RequestParam("categoryId") Integer categoryId,
                       @RequestParam("categoryName") String categoryName,
                       @RequestParam("categoryIcon") String categoryIcon){
        if(categoryId==null||categoryId<0||StringUtils.isEmpty(categoryName)||StringUtils.isEmpty(categoryIcon)){
            ResultGenerator.genFailResult("参数错误");
        }
        if(categoryService.updateCategory(categoryId,categoryName,categoryIcon)){
            return ResultGenerator.genSuccessResult("保存成功");
        }else {
            return ResultGenerator.genFailResult("保存错误");
        }
    }

}
