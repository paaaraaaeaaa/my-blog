package com.wanted.container.section03;

import com.wanted.container.section03.config.AppConfig;
import com.wanted.container.section03.service.PaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        /* comment. Bean 의 Scope */

        // IoC Container 생성
        // IoC container == Application Context == Bean Factory
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("===========singleton 확인!! ==============");

        // getBean() : 컨테이너 내부에 있는 객체를 꺼내오는 메서드
        PaymentService single = context.getBean("single", PaymentService.class);

        // 주문번호, 수량 전달
        boolean result = single.processPay("order-001",3);
        System.out.println("single 의 주문 ID = " + single.getOrderId());

        PaymentService single2 = context.getBean("single", PaymentService.class);
        /* getBean() 으로 객체를 2번 꺼냈는데 orderId 는 동일하다.
        *   === 1개의 인스턴스를 공유한다. == 싱글톤이다.
        * */
        System.out.println("single2 의 주문 ID = " + single2.getOrderId());

        System.out.println("===========prototype 확인!! ==============");
        PaymentService proto = context.getBean("proto", PaymentService.class);

        proto.processPay("order-002",3);
        System.out.println("proto 의 주문 ID = " + proto.getOrderId());

        PaymentService proto2 = context.getBean("proto", PaymentService.class);
        System.out.println("proto2 의 주문 ID = " + proto2.getOrderId());


    }

}
