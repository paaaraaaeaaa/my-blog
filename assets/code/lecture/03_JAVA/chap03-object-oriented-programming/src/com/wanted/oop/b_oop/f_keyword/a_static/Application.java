package com.wanted.oop.b_oop.f_keyword.a_static;

public class Application {

    /* comment. static 키워드
    *   static 이 붙은 변수/메소드는 객체 생성 시점에 초기화 되는 것이 아닌 어플리케이션 시작 시점에 초기화가 된다.
    *   static 은 정적이라는 의미를 가지고 있으며 일반적인 객체의 생명주기와는 다른 생명주기를 가지고 있게 된다.
    * */

    public static void main(String[] args) {

        // 객체(인스턴스) 생성 구문
        StaticFieldTest st1 = new StaticFieldTest();

        System.out.println("non-static 변수 값 확인 : " + st1.getNonStaticInt());
        // static 이 붙은 메서드는 클래스명.메소드명() 이렇게 호출한다.
        System.out.println("static 변수 값 확인 : " + StaticFieldTest.getStaticInt());

        // 각 변수를 1씩 증가시키는 메소드 호출
        st1.increaseNonStatic();
        st1.increaseStatic();

        System.out.println("non-static 변수 값 확인 : " + st1.getNonStaticInt());
        System.out.println("static 변수 값 확인 : " + StaticFieldTest.getStaticInt());

        StaticFieldTest st2 = new StaticFieldTest();
        System.out.println("st2 = non-static 변수 값 확인 : " + st2.getNonStaticInt());
        System.out.println("st2 = static 변수 값 확인 : " + StaticFieldTest.getStaticInt());

    }

}
