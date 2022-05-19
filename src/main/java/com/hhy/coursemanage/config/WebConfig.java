package com.hhy.coursemanage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: CourseManage
 * @description: web配置类
 * @author: PlusL
 * @create: 2022-05-06 11:21
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "D:/Learn/在校课程/高级WEB/data/upload");
    }

}
