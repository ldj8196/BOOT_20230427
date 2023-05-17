package com.example.boot_20230427.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.boot_20230427.filter.JwtFilter2;
import com.example.boot_20230427.filter.UrlFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean<JwtFilter2> filterRegistrationBean(JwtFilter2 jwtFilter2) {
        log.info("filter => {}", "filterConfig");
        FilterRegistrationBean<JwtFilter2> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(jwtFilter2);
        filterReg.addUrlPatterns("/api/student2/update.json");
        filterReg.addUrlPatterns("/api/student2/delete.json");

        // filterReg.addUrlPatterns("/api/student2/*"); // *는 전체 url

        return filterReg;

    }
    @Bean
    public FilterRegistrationBean<UrlFilter> filterRegistrationBean1(UrlFilter urlFilter) {
        log.info("filter => {}", "filterConfig");
        FilterRegistrationBean<UrlFilter> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(urlFilter);
        filterReg.addUrlPatterns("/api/student2/*"); // *는 전체 url
        return filterReg;
    }
}
