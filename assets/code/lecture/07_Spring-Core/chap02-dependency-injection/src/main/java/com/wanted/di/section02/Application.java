package com.wanted.di.section02;

import com.wanted.di.section02.config.AppConfig;
import com.wanted.di.section02.service.field.PaymentServiceField;
import com.wanted.di.section02.service.setter.PaymentServiceSetter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        /* comment.
        *   DI : 의존성 주입
        *   Spring 은 IoC 컨테이너 기능을 통해서 개발자가 직접 객체와 객체 간의 결합을 관리하는 것이 아닌,
        *   IoC 컨테이너가 자동으로 객체 간의 의존성을 주입하는 것을 의미한다. == (new 사용 X)
        *   ---
        *   1. field 주입
        *   2. setter 주입
        *   3. constructor 주입
        * */


        // IoC Container 생성
        // IoC container == Application Context == Bean Factory
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        PaymentServiceField pay = context.getBean("field", PaymentServiceField.class);
        pay.processPay("order-003",3);

        System.out.println("=======================================");

        PaymentServiceSetter pay2 = context.getBean("setter", PaymentServiceSetter.class);
        pay2.processPay("order-003",3);



    }

}
