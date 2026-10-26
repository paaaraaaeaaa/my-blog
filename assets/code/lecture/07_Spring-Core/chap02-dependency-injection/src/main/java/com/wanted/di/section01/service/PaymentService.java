package com.wanted.di.section01.service;


import com.wanted.di.section01.gateway.PaymentInterface;
import org.springframework.stereotype.Service;

/* comment.
 *   @Service 어노테이션은 @Component의 특수한 형태이다.
 *   MVC 패턴 중 Service 계층에 해당하는 Bean 에 붙이게 된다.
 *  */

@Service
public class PaymentService {

    private final PaymentInterface paymentInterface;
    /* comment.
     *   Bean은 기본적으로 Singleton 이다.
     *   가장 주의!!!!
     *   - Bean 이 상태(필드) 값을 가지게 되면 모든 요청이
     *   - 해당 필드 값을 공유하게 된다.
     *   - 따라서 필드에는 행위 위주의 클래스(ex) service, dao) 만 작성하고
     *   - 데이터 저장이 필요한 경우에는 각 요청별로 DTO 를 통해서 데이터를
     *   - 저장한다.
     *  */
    private String orderId;

    public PaymentService(PaymentInterface paymentInterface) {
        this.paymentInterface = paymentInterface;
    }

    public boolean processPay(String orderId, int amount) {

        System.out.println("결제 비지니스 로직 시작! : 주문 ID = " + orderId + ", 수량 = " + amount);

        this.orderId = orderId;

        boolean result = paymentInterface.processPay(orderId, amount);


        if(result) {
            System.out.println("결제 성공!");
        } else {
            System.out.println("결제 실패..");
        }
        return result;
    }

    public String getOrderId() {
        return this.orderId;
    }
}