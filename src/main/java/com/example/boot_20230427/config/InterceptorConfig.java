package com.example.boot_20230427.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    
    final HandlerInterceptor httpInterceptor; // 인터셉터 객체 생성
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(httpInterceptor)
                .addPathPatterns("/**") // 전체 페이지 인터셉터 동작
                .excludePathPatterns("/login**","/login/**"); // login 부분은 제외
                
    }
    
}
