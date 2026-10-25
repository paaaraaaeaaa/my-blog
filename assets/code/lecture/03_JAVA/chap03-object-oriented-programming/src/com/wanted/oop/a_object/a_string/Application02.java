package com.wanted.oop.a_object.a_string;

public class Application02 {
    public static void main(String[] args) {
        /* comment.
        *   문자열 만드는 방법
        *   1. String s = "" : 리터럴 형태
        *   2. String s2 = new String("");
        * */

        // 1. 리터럴 방식
        String str1 = "java";
        // 2. 객체 생성 방식
        String str2 = new String("java");
        String str3 = "java";
        String str4 = new String("java");

        System.out.println("str1 = " + str1);
        System.out.println("str2 = " + str2);

        System.out.println("동등비교 : " + (str1 == str2));
        System.out.println("동등비교 : " + (str1 == str3));
        System.out.println("동등비교 : " + (str2 == str4));

        // new : 할당 연산자
        // 해당 키워드를 만나게 되면 항상 새로운 공간을 만들게 된다.

        // 문자열 생성 방식과 상관 없이 동일한 문자열을 비교할 때 equlas() 메소드를 사용할 수 있다.
        System.out.println("equals() 활용 비교 : " + str1.equals(str2));
    }
}
