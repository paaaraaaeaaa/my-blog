package com.wanted.container.section02.gateway;

/* comment.
*   section01 에서 발생한 각 구현체별로 메서드 이름, 매개변수 타입 / 순서 문제를 interface 의 특징을 활용해서 해결
* */

public interface PaymentInterface {

    boolean processPay(String orderId, int amount);

}
