package com.example.boot_20230427.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomUser extends User {
    private String id;
    private String password;
    private Collection<GrantedAuthority> authorities;//role
    private String name;
    private int age;


    // User의 생성자 기본구조
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String id) {
        super(username, password, authorities);
    }


    // 기본구조에서 추가할 내용포함(이름, 나이)
    public CustomUser(String username, String password, Collection<GrantedAuthority> authorities, String name, int age) {
        super(username, password, authorities); // 부모 생성자 호출
        this.authorities = authorities;
        this.id = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }
     
}
