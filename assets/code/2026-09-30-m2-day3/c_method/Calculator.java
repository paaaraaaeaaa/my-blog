package com.wanted.c_method;

public class Calculator {

        /* comment.
        *   해당 계산기 클래스는 +, -, %, /, * 사칙연산을 잘 하는 메소드를 모아둔 클래스이다.
        * */

    // 2개의 정수를 전달 받아/ 비교해서 / 최솟값을 반환하는 메서드
    // 전달받는 정수를 담을 매개변수 2개 만들어야겠다!
    // 두 개의 매개변수를 대소를 비교하는 코드를 작성한다!
    // 최솟값은 정수로 반환? 그러니까 int 를 return 하면되겠다!

    public int minNumberOf(int a, int b){
        // 삼항연산자
        return (a > b) ? b : a; // return b;
    }

    public int maxNumberOf(int a, int b){
        // 삼항연산자
        return (a > b) ? a : b; // return b;
    }
}
