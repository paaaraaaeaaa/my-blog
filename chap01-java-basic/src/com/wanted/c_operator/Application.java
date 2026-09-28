package com.wanted.c_operator;

public class Application {

    public static void main(String[] args) {

        // 연산자 테스트용 데이터
        int a = 10;
        int b = 3;

        /*comment. 연산자
        *   산술 연산
        *   - (+, -, *, %, /)
        *   - % 프로그래밍에서는 모드연산이라는 것이 추가된다. 나머지를 구하는 연산자
        * */

        // "안녕" + 3 -> 문자열 + 숫자 -> 모두 문자열 취급이라서 안녕3 "안녕" + "3"
        // 문자열이 포함된 더하기 연산은 유의해야 한다.
        // 문자열이 + 연산을 만나면 피연산자를 문자열로 변환한다.
        System.out.println("덧셈 : " + (a + b));
        System.out.println("나눗셈 : " + (a / b));
        System.out.println("나머지 : " + (a % b));

        /*comment. 비교연산
        *   두 값을 비교하여 참/거짓을 반환하는 연산자
        *   - ( ==, !=, <, >, <=, >= )
        * */

        boolean isGreater = a > b;

        System.out.println("a > b : " + (a>b));
        // 프로그래밍에서 !의 의미는 NOT
        // a >= b
        System.out.println("a != b : " + (a!=b));

        /* comment. 논리 연산
        *   하나 이상의 "조건을 결합"하여 최종적인 참 또는 거짓을 평가한다.
        *   - (&&(and), ||(or), !(not))
        * */

        boolean isTrue = true;
        boolean isFalse = false;

        /*comment.
        *   과거 && 연산관련된 면접 질문
        *   A && B 식이 있을 때 A가 앞에 오는 것과 B가 앞에 오는 것에 대한 고찰?
        * */

        System.out.println("둘 다 참이니? : " + (isTrue && isFalse));
        System.out.println("둘 중 하나는 참이니? : " + (isTrue || isFalse));

        /*comment. 증감 연산
        *   변수의 값을 1씩 증가시키거나 감소시키는 연산자
        *   ++(증가), --(감소)
        *   전위 연산, 후위 연산
        * */
        // 번수: 프로그래밍에서 공간의 개념
        // 자료형 변수명 대입연산자 리터럴(값)
        int age = 20;

        System.out.println("초기 값 age : " + (age));
        System.out.println("++age : " + (++age));
        System.out.println("age : " + (age));
        System.out.println("age++ : " + (age++));
        System.out.println("age : " + (age));
    }
}
