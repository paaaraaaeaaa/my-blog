package com.wanted.container.section02.gateway;

public class KakaoPayGateway implements PaymentInterface{

    // 카카오페이 결제를 담당하는 메서드
    // 주문 번호 / 수량
    @Override
    public boolean processPay(String orderId, int amount) {
        System.out.println("카카오페이로 결제 시작! : 주문 ID = " + orderId + ", 수량 = " + amount);
        return true;
    }
}
