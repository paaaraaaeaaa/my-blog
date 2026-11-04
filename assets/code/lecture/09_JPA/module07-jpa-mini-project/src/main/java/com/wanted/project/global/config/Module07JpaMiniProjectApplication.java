package com.wanted.project.global.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * [Application] (제공 코드)
 * 역할 : 실행 진입점. 이 클래스의 main 을 실행해 서버를 켠다.
 * 책임 : 스프링 컨테이너를 띄우는 것만 담당한다. (스캔 범위 설정은 ContextConfig, JpaConfig 의 책임)
 */
@SpringBootApplication
public class Module07JpaMiniProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Module07JpaMiniProjectApplication.class, args);
    }

}
