/**
 * 统一Controller异常处理
 */
package com.example.demo.handler;

import com.example.demo.controller.AdminController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    /**
     * Controller异常处理
     *在Controller类中只能处理当前的Controller类
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception e) throws Exception {
        //打印日志
        logger.error("Request URL:{},Exception :{}",request.getRequestURL(),e.getMessage());
        //过滤指定状态码异常
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("e",e);
        mv.setViewName("error/error");
        return mv;
    }
}
