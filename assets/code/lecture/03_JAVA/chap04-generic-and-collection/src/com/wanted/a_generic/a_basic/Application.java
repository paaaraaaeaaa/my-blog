package com.wanted.a_generic.a_basic;

public class Application {

    public static void main(String[] args) {

        /* comment. Generic 이란?
        *   제네릭은 데이터의 타입을 일반화한다는 의미이다.
        *   클래스나 메소드에서 사용할 낸부 데이터 타입을 컴파일 시점에 지정하는 방법을 의미한다.
        *   컴파일 시점에 미리 타입에 대한 검사를 진행하여, 클래스나 메소드 내부에서 사용되는 객체의 타입 안정성을 높일 수 있다.
        * */

        GenericTest gt = new GenericTest();
        gt.setValue(1);
        System.out.println("gt = " + gt.getValue());
        System.out.println("=============================");
        gt.setValue("안녕하세요");
        System.out.println("gt = " + gt.getValue());
        System.out.println("=============================");
        GenericTest<String> gt2 = new GenericTest<>();
        gt2.setValue("문자열");
        System.out.println("gt2 = " + gt2.getValue());
//        gt2.setValue(1);

        /* comment.
        *   <> 제네릭의 다이아몬드 연산자 내부에는 기본자료형이 들어갈 수 없다.
        *   - Wrapper 클래스
        *   - 기본자료형(int, char, boolean) 을 인스턴스화 (== 참조자료형 화) 한 객체라고 본다.
        *   int -> Integer
        *   byte -> Byte
        *   short -> Short
        *   boolean -> Boolean
        *   char -> Character
        * */
//        GenericTest<int> gt3 = new GenericTest<int>();
        GenericTest<Integer> gt3 = new GenericTest<>();
        gt3.setValue(1);

    }

}
