package com.wanted.di.section02.service.setter;


import com.wanted.di.section02.gateway.PaymentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("setter")
public class PaymentServiceSetter {

    private PaymentInterface paymentInterface;

    /* comment.
    *   @Autowired 를 setter 메서드에 작성해서 의존성 주입
    *   ---
    *   단점
    *   1. final 키워드 사용할 수 없다.
    *   2. setter 메서드가 호출되지 않으면 null 인 상태이다.
    *   장점
    *   1. 선택적 의존관계를 가질 수 있다.
    * */

    @Autowired
    public void setPaymentInterface(PaymentInterface paymentInterface) {
        this.paymentInterface = paymentInterface;
    }

    public boolean processPay(String orderId, int amount) {



        System.out.println("결제 비지니스 로직 시작! : 주문 ID = " + orderId + ", 수량 = " + amount);


        boolean result = paymentInterface.processPay(orderId, amount);


        if(result) {
            System.out.println("결제 성공!");
        } else {
            System.out.println("결제 실패..");
        }
        return result;
    }

}