package com.wanted.container.section01.service;

import com.wanted.container.section01.gateway.KakaoPayGateway;
import com.wanted.container.section01.gateway.NaverPayGateway;

public class PaymentService {

    // 네이버 페이 구현
//    private NaverPayGateway naverPayGateway;
    private KakaoPayGateway kakaoPayGateway;

    public PaymentService() {
//        this.naverPayGateway = new NaverPayGateway();
        this.kakaoPayGateway = new KakaoPayGateway();
    }

    public boolean processPay(String orderId, int amount) {
        System.out.println("결제 비즈니스 로직 시작! : 주문 ID = "+orderId+", 수량 = " +amount);

        // 네이버 페이 담당 메서드 호출
//        boolean result = naverPayGateway.pay(orderId,amount);
        boolean result = kakaoPayGateway.pay(orderId,amount);

        if (result) {
            System.out.println("결제 성공!");
        } else {
            System.out.println("결제 실패..");
        }
        return result;
    }
}