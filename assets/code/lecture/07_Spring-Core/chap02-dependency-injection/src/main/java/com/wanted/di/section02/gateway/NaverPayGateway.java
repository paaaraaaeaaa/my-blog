package com.wanted.di.section02.gateway;

import org.springframework.stereotype.Component;

@Component
public class NaverPayGateway implements PaymentInterface {

    // 네이버페이 결제를 담당하는 메서드
    // 주문 번호 / 수량
    @Override
    public boolean processPay(String orderId, int amount) {
        System.out.println("네이버페이로 결제 시작! : 주문 ID = " + orderId + ", 수량 = " + amount);
        return true;
    }
}
