package com.yshen.studyspringbootvideo.demo998.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @author 郑悦恺
 * @Classname FileSize
 * @Description TODO
 * @Date 2020/2/28 18:08
 */
@Configuration
public class FileSize {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        //factory.setMaxFileSize(1024);
        //单个文件最大
        factory.setMaxFileSize(DataSize.parse("102400KB"));
        //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("1024000KB"));
        return factory.createMultipartConfig();
    }
}
