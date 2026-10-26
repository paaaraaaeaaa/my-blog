package com.wanted.di.section02.service.field;


import com.wanted.di.section02.gateway.PaymentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* comment.
 *   @Service 어노테이션은 @Component의 특수한 형태이다.
 *   MVC 패턴 중 Service 계층에 해당하는 Bean 에 붙이게 된다.
 *  */

@Service("field")
public class PaymentServiceField {

    /* comment.
    *   @Autowired 어노테이션은 IoC 컨테이너에 있는 Bean을 찾아서 사용하는 위치에 객체를 주입하겠다는 의미이다.
    *   ---
    *   필드 주입의 단점
    *   - final 키워드를 사용할 수 없다.
    *   - 필드에 다른 객체를 직접 생성해서 대입해도 컴파일 에러가 발생하지 않는다.
    * */

    @Autowired
    private PaymentInterface paymentInterface;

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