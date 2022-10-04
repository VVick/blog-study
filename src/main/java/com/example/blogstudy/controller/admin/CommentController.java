package com.example.blogstudy.controller.admin;

import com.example.blogstudy.service.CommentService;
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
 * @ClassName CommentController
 * @Description TODO
 * @Author OuYangCong
 * @Date 2022/9/11
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //跳转到评论界面
    @GetMapping("/comments")
    public String List(HttpServletRequest request){
        request.setAttribute("path","comments");
        return "admin/comment";
    }

    //显示评论列表
    @GetMapping("/comments/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        //分页查询参数
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(commentService.getBlogCommentPage(pageUtil));
    }

    //评论审核
    @PostMapping(value = "/comments/checkDone")
    @ResponseBody
    public Result check(@RequestBody Integer[] ids){
        if(ids.length<1){
            return ResultGenerator.genFailResult("参数异常");
        }
        if(commentService.checkDone(ids)){
            return ResultGenerator.genSuccessResult("审核成功");
        }else {
            return ResultGenerator.genFailResult("审核失败");
        }
    }

    //回复评论
    @PostMapping(value = "/comments/reply")
    @ResponseBody
    public Result reply(@RequestParam("commentId") Long commentId,
                        @RequestParam("replyBody") String replyBody){
        if(commentId==null||commentId<0||StringUtils.isEmpty(replyBody)){
            return ResultGenerator.genFailResult("参数异常");
        }
        if(commentService.reply(commentId,replyBody)){
            return ResultGenerator.genSuccessResult("回复成功");
        }else {
            return ResultGenerator.genFailResult("回复失败");
        }

    }

    @PostMapping(value = "/comments/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if(ids.length<1){
            return ResultGenerator.genFailResult("参数异常");
        }
        if(commentService.deleteBlog(ids)){
            return ResultGenerator.genSuccessResult("删除成功");
        }else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
