package com.wanted.container.section03.config;

/* comment.
*   IoC 컨테이너에게 등록할 객체를 모아둔 설정 클래스
*   Bean : Bean 은 Spring 에서 객체를 의미하는 단어.
* */

import com.wanted.container.section03.gateway.KakaoPayGateway;
import com.wanted.container.section03.gateway.NaverPayGateway;
import com.wanted.container.section03.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    /* comment.
    *   @Bean 의 Scope 확인하기!
    *   1. Bean 은 기본적으로 싱글톤 스코프를 갖는다.
    *   - 객체는 기본적으로 컨테이너 내부에서 단일 인스턴스이다.
    *   - 모든 요청에 동일한 객체를 반환한다.
    *   - 1-1) 메모리 효율적이다.
    *   - 1-2) 같은 인스턴스이기 때문에 동일한 동작을 예상할 수 있다.
    * */

    @Bean
    public KakaoPayGateway kakaoPayGateway() {
        return new KakaoPayGateway();
    }

    @Bean
    public NaverPayGateway naverPayGateway() {
        return new NaverPayGateway();
    }

    @Bean("single") // default 메서드명이지만, 직접 이름 지정 가능
    @Scope("singleton") // prototype 으로 지정하면 요청 시마다 매번 새로운 인스턴스를 반환하게 된다.
    public PaymentService paymentService() {
        return new PaymentService(kakaoPayGateway());
    }

    @Bean("proto") // default 메서드명이지만, 직접 이름 지정 가능
    @Scope("prototype") // default 설정은 싱글톤이다.
    public PaymentService paymentService2() {
        return new PaymentService(kakaoPayGateway());
    }

}
