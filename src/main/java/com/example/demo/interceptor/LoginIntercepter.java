package com.example.demo.interceptor;



import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
@Controller
public class LoginIntercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断Seession 对象
        if(request.getSession().getAttribute("admin") == null){
            //重定向登录页面
            response.sendRedirect("/index");
            //不执行跳转页面
            return  false;
        }
        return true;
    }
}
