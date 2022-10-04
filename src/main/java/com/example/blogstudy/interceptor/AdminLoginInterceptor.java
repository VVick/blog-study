package com.example.blogstudy.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName AdminLoginInterceptor
 * @Description TODO
 * @Author OuYangCong
 * @Date 2022/9/10
 * @Version 1.0
 **/
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行了拦截器的preHandle方法");
        //获取请求路径
        String requestServletPath=request.getServletPath();
        if(requestServletPath.startsWith("/admin") && request.getSession().getAttribute("loginUser")==null){
            request.getSession().setAttribute("errorMsg","请重新登录");
            response.sendRedirect(request.getContextPath()+"/admin/login");
            return  false;
        }
        else {
            request.getSession().removeAttribute("errorMsg");
            return true;
        }

//        String requestServletPath = request.getServletPath();
//        if (requestServletPath.startsWith("/admin") && null == request.getSession().getAttribute("loginUser")) {
//            request.getSession().setAttribute("errorMsg", "请重新登陆");
//            response.sendRedirect(request.getContextPath() + "/admin/login");
//            return false;
//        } else {
//            request.getSession().removeAttribute("errorMsg");
//            return true;
//        }
    }

    /***
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("执行了拦截器的postHandle方法");
    }

    /***
     * 整个请求结束之后被调用，也就是在DispatchServlet渲染了对应的视图之后执行（主要用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("执行了拦截器的afterCompletion方法");
    }

}
