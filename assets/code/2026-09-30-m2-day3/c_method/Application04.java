package com.wanted.c_method;

public class Application04 {

    public static void main(String[] args) {

        /* comment.
        *   다른 클래스에 존재하는 메소드 호출하기
        * */
        int first = 10;
        int second = 20;

        // 다른 영역에 있는 메서드를 호출해야 한다.
        // 1. 호출 준비
        Calculator calc = new Calculator();
        // 2. 최솟값 메서드 호출
        int min = calc.minNumberOf(first, second); // b = 20

        System.out.println(first + ", " + second + " 중 최솟값은 : " + min + "입니다.");

        // 3. 최댓값 메서드 호출
        int max = calc.maxNumberOf(first, second);
        System.out.println(first + ", " + second + " 중 최댓값은 : " + max + "입니다.");

    }
}
