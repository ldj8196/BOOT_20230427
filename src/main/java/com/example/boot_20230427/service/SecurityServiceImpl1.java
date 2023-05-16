package com.example.boot_20230427.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.example.boot_20230427.entity.library.Student2;

import com.example.boot_20230427.repository.library.Student2Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 로그인에서 로그인 버튼 -> loadUserByusername으로 이메일 정보를 넘김
// Student2와 연동되는 서비스

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityServiceImpl1 implements UserDetailsService {

    final String format = "SecurityServiceImpl1 => {}";
    final Student2Repository s2Repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(format, username);
        // 이메일을 전달해서 정보를 받아옴 암호까지 받아와서 User타입으로 변환해서 리턴하면
        // 시큐리티가 비교후에 로그인처리를 자동수행
        Student2 obj = s2Repository.findById(username).orElse(null);
        log.info(format, obj);
        if(obj != null) { // 가져올 정보가 있었다. 존재하는 아이디가 있다
            // Student2 Repository이용하여 
            return User.builder().username(obj.getEmail()).password(obj.getPassword()).roles("STUDENT2").build();
        }
        
        // 이메일이 없는 경우 User로 처리 
        return User.builder()
            .username("_")
            .password("_")
            .roles("_")
            .build();
        // DB 연동
        
    }
    
}
