package com.wanted.a_controlflow;

public class Application04 {

    public static void main(String[] args) {

        /* comment.
        *   switch 문
        *   - 다중 조건 상황에서 if-else 구문의 단점을 대체하는 문법이다.
        *   형식 : switch(식) { case 값 : 실행코드; break; default : 기본코드; }
        *   - 식 : 정수, 문자열 등 비교가 가능한 타입이 들어갈 수 있다.
        *   - case : 식과 비교하여 값이 식과 일치할 때 실행할 코드를 정의
        *   - break : 분기를 종료하는 문법, switch 블럭을 탈출
        *  */

        // 1~12 월 중 month 값에 따라 몇 월인지 알려주는 프로그램을 개발해보자.
        int month = 1;

        switch (month) {
            case 1 :
                System.out.println("1월");
                break;
            case 2 :
                System.out.println("2월");
                break;
            case 3 :
                System.out.println("3월");
                break;
            default: // 조건문에서 else 역할을 하는 것이 default
                System.out.println("그 외!");
                break;

        }
    }
}
