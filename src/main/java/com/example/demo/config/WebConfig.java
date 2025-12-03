package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadPath = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "productos" + File.separator;
        System.out.println("Upload path real: " + uploadPath);

        registry.addResourceHandler("/uploads/productos/**")
                .addResourceLocations("file:" + uploadPath);
    }
}
