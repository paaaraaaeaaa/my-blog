package com.wanted.oop.a_object.a_string;

public class Application01 {
    public static void main(String[] args) {

        /* comment.
        *   자료형의 종류
        *   - 1. 기본 자료형(int, char, double)
        *   - 2. 참조 자료형
        *   - 3. 사용자 정의의 자료형
        * */

        String str1 = "apple";
        System.out.println("str1 의 길이! : " + str1.length());
        /* comment.
        *   String 클래스에서 많이 쓰이는 메서드
        *   - length() : 문자열의 길이를 int로 반환
        *   - charAt(index) : 문자열을 문자로 변환
        * */

        // apple 문자열을 1개씩 출력하는 프로그램
        // a
        // p
        // p
        // l
        // e

        // index 숫자체계
        // 0부터 시작하는 숫자체계를 의미한다.
        // "abc" -> a:0, b:1, c:2
        for (int i = 0; i < str1.length(); i++) {
            System.out.println(str1.charAt(i));
        }

        String trimStr = "   java   ";
        System.out.println("공백 제거 전 : #" + trimStr + "#");
        System.out.println("공백 제거 후 : #" + trimStr.trim() + "#");

        // 공부법
        // 1. 메소드를 다 외운다 -> 사용한다
        // 2. 사용해보고 -> 출력해보고 -> 이해한다.



    }
}
