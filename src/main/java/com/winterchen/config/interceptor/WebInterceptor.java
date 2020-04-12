package com.winterchen.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 作者：齐文杰
 * 时间：2018/9/16
 */
@Configuration
public class WebInterceptor extends WebMvcConfigurerAdapter {
    @Bean
    public UserHandlerInterceptor userInterceptor() {
        return new UserHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(userInterceptor()).addPathPatterns("/**").excludePathPatterns("/Account/**")
//                .excludePathPatterns("/error").excludePathPatterns("/outcall/saveClueNumConfig");

        // registry.addInterceptor(new
        // LoginUserIntercept()).addPathPatterns("/**").excludePathPatterns("/Account/**")
        // .excludePathPatterns("/common/permissions.do");

        // new com.xy.login.intercept.LoginUserIntercept();
		/*
		 * registry.addInterceptor(new
		 * LoginUserIntercept()).addPathPatterns("/**").excludePathPatterns(
		 * "/Account/**")
		 * .excludePathPatterns("/common/**").excludePathPatterns("/error");
		 */

        // registry.addInterceptor(new
        // UserHandlerInterceptor()).excludePathPatterns("/OutCall/**");
        // registry.addInterceptor(new
        // UserHandlerInterceptor()).excludePathPatterns("/error");
    }
}
