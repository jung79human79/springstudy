package com.smhrd.myapp;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링부트 프로젝트 시작지점
@SpringBootApplication

// @SpringBootConfiguration
// :스프링부트의 전반적인 환경설정을 담당
// @EnableAutoConfiguration
// : jar파일을 경로상에 놔두기만(load) 하면 필요한 객체들을 자동으로 생성 사용
// : 전에는 bean에서 객체를 만들었다
// @ComponentScan : 해당경로를 기준으로 하위 패키지에 있는 bean을 자동으로 SpringBootContainer에 등록
// 위 3개의 anno 을 하나로 합해놓은 @SpringBootApplication
public class SpringBoot1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot1Application.class, args);
	}

}
