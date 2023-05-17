package com.example.boot_20230427.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.boot_20230427.entity.library.Student2;
import com.example.boot_20230427.restcontroller.library.JwUtil2;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

// 회원정보수정, 회원탈퇴, 비번변경 토큰이 필요한 경우에 대한 처리
// 컨트롤러 전에 수행되는 클래스 => 토큰의 유효성을 검증하고 실패시 컨트롤러 진입X
// url에 주소에 따라 분류함.
@Component
@RequiredArgsConstructor
public class JwtFilter2 extends OncePerRequestFilter {
    
    @Autowired JwUtil2 jwUtil2;
    ObjectMapper objectMapper = new ObjectMapper(); // json으로 변환하는 라이브러리

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, Object> retMap = new HashMap<>();

            String token = request.getHeader("token");
            if(token == null) { // {stauts:-1, message:"token null"} 로 반환됨 ( 키가 없음 )
                retMap.put("status", -1);
                retMap.put("message", "token null");
                String result = objectMapper.writeValueAsString(retMap);
                response.getWriter().write(result);
                return; // 메소드 종료
            }
            if(token.length() <= 0) { // {stauts:-1, message:"token is empty"} 로 반환됨 ( 내용이 없음)
                retMap.put("status", -1);
                retMap.put("message", "token is empty");
                String result = objectMapper.writeValueAsString(retMap);
                response.getWriter().write(result);
                return; // 메소드 종료
            }

            Student2 obj = jwUtil2.checkJwt(token);
            if(obj == null) {
                retMap.put("status", -1);
                retMap.put("message", "token error");
                String result = objectMapper.writeValueAsString(retMap);
                response.getWriter().write(result);
                return;
            }
            // 아래 명령어가 실행되어야 정상적인 컨트롤러로 진입가능
            filterChain.doFilter(request, response);
        } 
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(-1, "token error");
        }
    }
    
}
