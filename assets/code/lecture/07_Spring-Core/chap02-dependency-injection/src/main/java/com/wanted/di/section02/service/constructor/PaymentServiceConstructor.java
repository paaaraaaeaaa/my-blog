package com.wanted.di.section02.service.constructor;


import com.wanted.di.section02.gateway.PaymentInterface;
import org.springframework.stereotype.Service;

/* comment.
 *   @Service 어노테이션은 @Component의 특수한 형태이다.
 *   MVC 패턴 중 Service 계층에 해당하는 Bean 에 붙이게 된다.
 *  */

@Service("constructor")
public class PaymentServiceConstructor {

    /* comment.
    *   @Autowired 어노테이션을 생성자에 붙인다.
    *   생성자 주입 방식은 생성자가 1게뿐이라면 자동으로 @Autowired 를 작성하지 않더라도 의존성이 주입된다.
    *   ---
    *   장점
    *   1. final 키워드를 통해 의존성이 변경되는 것을 방지(불변성 보장)
    *   2. 필수 의존성 강제화
    *   - final 키워드를 작성하면 초기화를 반드시 해주어야 하는데, 생성자로 주입하게 되면 객체의 생성 시점에 의존 관계를 바로 정의할 수 있다.
    *   ---
    *   위 특징들로 인해서 생성자를 통한 의존성 주입 방식이 Spring 공식 권장 방식이다.
    * */

    private final PaymentInterface paymentInterface;

//    @Autowired
    public PaymentServiceConstructor(PaymentInterface paymentInterface) {
        this.paymentInterface = paymentInterface;
    }


    public boolean processPay(String orderId, int amount) {

//        paymentInterface = new NaverPayGateway();

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