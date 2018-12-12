package com.inpivota.omega.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(
            ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**")
                .addResourceLocations("/WEB-INF/build/static/");
        registry.addResourceHandler("/*.js")
                .addResourceLocations("/WEB-INF/build/");
        registry.addResourceHandler("/*.json")
                .addResourceLocations("/WEB-INF/build/");
        registry.addResourceHandler("/*.ico")
                .addResourceLocations("/WEB-INF/build/");
        registry.addResourceHandler("/index.html")
                .addResourceLocations("/WEB-INF/build/index.html");
    }
}