package com.myapp.Configuration;


import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 总路由控制器配置
 * @author milk
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 配置URL路径和对应的视图模板
     *
     * @param registry 视图控制器注册器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/html/homePage.html");
        registry.addViewController("/home").setViewName("forward:/html/homePage.html");
        registry.addViewController("/homePage").setViewName("forward:/html/homePage.html");
        registry.addViewController("/advice").setViewName("forward:/html/advice.html");
        registry.addViewController("/result").setViewName("forward:/html/result.html");
        registry.addViewController("/chat").setViewName("forward:/html/chat.html");
        registry.addViewController("/login").setViewName("forward:/html/login.html");
        registry.addViewController("/register").setViewName("forward:/html/register.html");
        registry.addViewController("/show").setViewName("forward:/html/showPdf.html");
    }
}
