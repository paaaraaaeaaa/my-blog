package com.wanted.container.section02.service;

import com.wanted.container.section02.gateway.KakaoPayGateway;
import com.wanted.container.section02.gateway.PaymentInterface;

public class PaymentService {

    private final PaymentInterface paymentInterface;

    public PaymentService(PaymentInterface paymentInterface) {
        this.paymentInterface = paymentInterface;
    }

    public boolean processPay(String orderId, int amount) {
        System.out.println("결제 비즈니스 로직 시작! : 주문 ID = "+orderId+", 수량 = " +amount);

        boolean result = paymentInterface.processPay(orderId, amount);

        if (result) {
            System.out.println("결제 성공!");
        } else {
            System.out.println("결제 실패..");
        }
        return result;
    }
}