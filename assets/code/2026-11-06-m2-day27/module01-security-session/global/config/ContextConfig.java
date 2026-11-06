package com.wanted.securitysession.global.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/* comment.
*   ModelMapper Bean 제거
*   -> Entity <-> DTO 변환은 정적 팩토리 메서드로 처리한다. (LoginUserDTO.from, User.create)
*  */
@Configuration
@ComponentScan(basePackages = "com.wanted.securitysession")
public class ContextConfig {

}
