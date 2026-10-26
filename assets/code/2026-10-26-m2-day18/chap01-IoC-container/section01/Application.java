package com.wanted.container.section01;

import com.wanted.container.section01.service.PaymentService;

public class Application {

    public static void main(String[] args) {

        System.out.println("=========Spring 없이 객체를 직접 생성했을 때=========");

        /* comment.
        *   우리 프로젝트는 결제 시스템이 있으며, KakaoPay, NaverPay 가 있다.
        *   Application 에서는 PaymentService 를 호출하면 상황에 맞는 Pay 를 호출해서 결제가 되는 시나리오를 구성한다.
        * */

        // 객체 생성
        PaymentService paymentService = new PaymentService();
        // 주문번호, 수량 전달
        boolean result = paymentService.processPay("order-001",3);

        System.out.println("결제 결과 : " + result);

        /* comment.
        *   현재 우리 프로그램은 NaverPay 를 사용하고 있다.
        *   Naver -> Kakao Pay 로 변경하자는 요구사항이 추가됨.
        * */

        /* comment.
        *   현재 우리 프로그램은 NaverPay 를 사용하고 있다.
        *   Naver -> Kakao Pay 로 변경하자는 요구사항이 추가됨.
        *   현재 코드 문제
        *   1. 요구사항 변경 시 결제 구현체를 직접 변경해야 된다.
        *   2. 만약 메소드 이름이 다르거나, 매개변수가 다르면 전부 수정해야 된다.
        *   ---
        *   위 문제사항은 SOLID 원칙 중 "개방-폐쇄" 원칙을 위반했다고 표현한다.
        *   - 1개를 수정했을 때 여러 코드의 수정이 필요한 상황
        *   - 소프트웨어는 확장에는 열려있어야 하며, 변경에는 닫혀있어야 한다.
        * */

    }

}
