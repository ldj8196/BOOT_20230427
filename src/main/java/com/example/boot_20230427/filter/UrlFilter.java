package com.example.boot_20230427.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 로그인 후에 이전페이지로 되돌아 가기 위해서 현재의 페이지를 저장하는 필터
// 현재 페이지중에서, 로그인, 로그아웃은 제외시킴
@Component
@RequiredArgsConstructor
@Slf4j
public class UrlFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // ex) /ROOT/api/student2/update.json?id=a
            String contextPath = request.getContextPath(); // /ROOT
            String path = request.getServletPath(); // /api/student2/update.json
            String query = request.getQueryString(); // 

            log.info("{},{},{}", contextPath,path,query);

            if( !path.contains("login") && path.contains("logout")) {
                HttpSession httpSession = request.getSession();

                if(query == null) {
                    httpSession.setAttribute("url",path);
                }
                else {
                    httpSession.setAttribute("url", path+"?"+query);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
