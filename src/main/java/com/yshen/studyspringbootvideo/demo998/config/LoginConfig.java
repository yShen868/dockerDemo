package com.yshen.studyspringbootvideo.demo998.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 郑悦恺
 * @Classname LoginConfig
 * @Description TODO
 * @Date 2020/2/29 14:17
 */
//@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new AdminInterceptor());
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(                         //添加不拦截路径
                "/login",
                "/loginin",
//                "/**/*.html",
                "/**/*.js",
                "/**/*.css",
                "/**/*.jpeg",
                "/**/*.jpg",
                "/**/*.png",
//                "/images/login",
                "/getCode"
//                "/**/*.*"

        );
    }
}