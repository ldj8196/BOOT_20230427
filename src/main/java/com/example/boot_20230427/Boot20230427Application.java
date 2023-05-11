package com.example.boot_20230427;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// 새로운 패키지를 생성하기 역할을 부여하면 반드시 실행파일에 등록해야함
// classpath ==> resources와 동일함.
@SpringBootApplication
@PropertySource(value = {"classpath:global.properties"}) // 직접만든 환경설정 파일 위치
@MapperScan(basePackages = {"com.example.boot_20230427.mapper"}) // 맵퍼 위치 설정
@ComponentScan(basePackages = {"com.example.boot_20230427.controller",
							   "com.example.boot_20230427.controller.jpa",
							   "com.example.boot_20230427.controller.mybatis",
							   "com.example.boot_20230427.service",
							   "com.example.boot_20230427.config",
							   "com.example.boot_20230427.restcontroller"}) // 컨트롤러, 서비스 위치, 시큐리티 환경설정 
@EntityScan(basePackages = {"com.example.boot_20230427.entity"}) // 엔티티 위치
@EnableJpaRepositories(basePackages = {"com.example.boot_20230427.repository"}) // 저장소 위치
public class Boot20230427Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot20230427Application.class, args);         
	}
}
