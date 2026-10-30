package com.wanted.thymeleaf.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * @SpringBootApplication은 자신이 위치한 패키지(com.wanted.thymeleaf.config)와 그 하위만 스캔한다.
 * controller 패키지는 config 하위가 아니므로 상위 패키지를 기준으로 스캔 범위를 따로 지정한다.
 */
@Configuration
@ComponentScan(basePackages = "com.wanted.thymeleaf")
public class ContextConfiguration {
}
