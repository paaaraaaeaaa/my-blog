package com.wanted.project.global.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * [ContextConfig] (제공 코드)
 * 역할 : com.wanted.project 하위의 Bean(@Controller, @Service, @ControllerAdvice 등)을 스캔한다.
 * 책임 : 실행 클래스가 global.config 패키지에 있어 기본 스캔 범위가 좁기 때문에, 스캔 범위를 프로젝트 전체로 넓힌다.
 */
@Configuration
@ComponentScan(basePackages = "com.wanted.project")
public class ContextConfig {

}
