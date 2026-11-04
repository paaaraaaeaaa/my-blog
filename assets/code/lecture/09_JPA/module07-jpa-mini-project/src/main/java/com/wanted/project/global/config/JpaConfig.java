package com.wanted.project.global.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
 * [JpaConfig] (제공 코드)
 * 역할 : com.wanted.project 하위의 Entity / Repository 를 스캔한다.
 * 책임 : JPA 스캔 설정만 담당한다. (일반 Bean 스캔은 ContextConfig 의 책임)
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.wanted.project")
@EntityScan(basePackages = "com.wanted.project")
public class JpaConfig {
}
