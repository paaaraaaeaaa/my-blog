package com.wanted.container.section02.config;

/* comment.
*   IoC 컨테이너에게 등록할 객체를 모아둔 설정 클래스
*   Bean : Bean 은 Spring 에서 객체를 의미하는 단어.
* */

import com.wanted.container.section02.gateway.KakaoPayGateway;
import com.wanted.container.section02.gateway.NaverPayGateway;
import com.wanted.container.section02.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /* comment.
    *   @Bean 어노테이션을 사용하면 컨테이너 내부에서 그 객체의 이름은 메서드명이 기본값이 된다.
    * */

    @Bean
    public KakaoPayGateway kakaoPayGateway() {
        return new KakaoPayGateway();
    }

    @Bean
    public NaverPayGateway naverPayGateway() {
        return new NaverPayGateway();
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(kakaoPayGateway());
    }
}
