package com.wanted.container.section01.gateway;

public class NaverPayGateway {

    // 네이버페이 결제를 담당하는 메서드
    // 주문 번호 / 수량
    public boolean pay(String orderId, double amount) {
        System.out.println("네이버페이로 결제 시작! : 주문 ID = " + orderId + ", 수량 = " + amount);
        return true;
    }

}
