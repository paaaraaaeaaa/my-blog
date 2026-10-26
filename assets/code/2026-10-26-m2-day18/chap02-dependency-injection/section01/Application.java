package com.wanted.di.section01;

import com.wanted.di.section01.config.AppConfig;
import com.wanted.di.section01.service.PaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        /* comment.
        *   AppConfig 파일 하위에 Bean 들을 정의해서 IoC 컨테이너에 등록했었다.
        *   ---
        *   문제 제기! : 만약 Bean 이 100개 라면?
        * */


        // IoC Container 생성
        // IoC container == Application Context == Bean Factory
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        PaymentService pay = context.getBean("paymentServiceField", PaymentService.class);
        pay.processPay("order-003",3);



    }

}
