package com.djq.springGarden.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 * @author yerong
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    /**
     * 在配置文件中配置的文件保存路径
     */
//    @Value("${img.location}")
//    private String location;

    /**
     * 映射图片文件目录
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/upload/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/");
    }

//    @Bean
//    public MultipartConfigElement multipartConfigElement(){
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //文件最大KB,MB
//        factory.setMaxFileSize("2MB");
//        //设置总上传数据总大小
//        factory.setMaxRequestSize("10MB");
//        return factory.createMultipartConfig();
//    }
}