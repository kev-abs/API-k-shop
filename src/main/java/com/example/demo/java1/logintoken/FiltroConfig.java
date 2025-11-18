package com.example.demo.java1.logintoken;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltroConfig {

    @Bean
    public FilterRegistrationBean<JwtFiltro> jwtFiltroRegistration(JwtFiltro filtro) {
        FilterRegistrationBean<JwtFiltro> registroFiltro = new FilterRegistrationBean<>();
        registroFiltro.setFilter(filtro);
        registroFiltro.addUrlPatterns( "/puntos/*");
        registroFiltro.setOrder(1);
        return registroFiltro;
    }
}

