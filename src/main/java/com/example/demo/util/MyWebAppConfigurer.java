/**
 * 拦截器
 *
 */
package com.example.demo.util;


import com.example.demo.interceptor.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 注册拦截器
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new LoginIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns("/index")
                .excludePathPatterns("/dengluAdmin")
                .excludePathPatterns("/addAdmin")
                .excludePathPatterns("/zuce")
                .excludePathPatterns("/getVerify");
        super.addInterceptors(registry);
    }
}
