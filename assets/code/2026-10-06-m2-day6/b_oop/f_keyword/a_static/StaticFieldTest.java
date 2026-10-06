package com.wanted.oop.b_oop.f_keyword.a_static;

public class StaticFieldTest {

    // static 키워드 확인을 위한 2개의 필드 선언
    private int nonStaticInt;
    private static int staticInt;

    // 인스턴스 생성 시 호출되는 기본 생성자.
    // 필드를 초기화하거나, 인스턴스 생성 시 가장 먼저 해야 할 작업이 있다면 생성자 내부에 작성.
    public StaticFieldTest() {}

    /* word. alt + insert */

    public static int getStaticInt() {
        return staticInt;
    }

    public int getNonStaticInt() {
        return nonStaticInt;
    }

    // 각 필드를 호출 시 1씩 증가시키는 메서드
    public void increaseNonStatic() {
        this.nonStaticInt++;
    }
    public void increaseStatic() {
        // static 키워드가 붙은 변수는 클래스명.변수명 으로 접근이 가능하다.
        // this 는 사용되지 않는다.
        StaticFieldTest.staticInt++;
    }

}
