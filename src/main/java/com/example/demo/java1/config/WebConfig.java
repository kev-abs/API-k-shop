package com.example.demo.java1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private static final String UPLOAD_PATH = "file:///C:/xampp/htdocs/API-k-shop/uploads/productos/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/uploads/productos/**")
                .addResourceLocations(UPLOAD_PATH)
                .setCachePeriod(3600)
                .resourceChain(true);
    }
}