package com.wanted.securitysession.global.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.wanted.securitysession")
@EntityScan(basePackages = "com.wanted.securitysession")
public class JpaConfig {
}
