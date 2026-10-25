package com.wanted.c_method;

public class Application03 {
    public static void main(String[] args) {

        /* comment.
        *   전달인자와 매개변수를 이용한 매서드 호출
        *
        * */
        Application03 app3 = new Application03();
        int x = app3.testMethod(40, "문자열", true, 'a'); // 40이 된다.

        System.out.println("당신의 나이는 " + x + "세 입니다.");

    }

    /* comment.
    *   method 의 형태
    *   [접근제어자] [반환타입] 메소드명([매개변수 타입 매개변수 명]) {
    *       실행할 코드
    *       [return 반환값;] // 반환타입이 void가 아니라면 return 은 생략될 수 없다.
    *   }
    *   접근제어자
    *   - public : 모든 클래스에서 접근 가능
    *   - private : 같은 클래스 내부에서만 접근 가능
    *   - protected : 같은 패키지 | 자식 클래스에서 접근 가능
    *   - default (작성하지 않을 때)
    * */

    // 1. void -> int 반환타입을 지정하는 자리
    public int testMethod(int a, String s, boolean b, char c) {
        return a;
    }

}
