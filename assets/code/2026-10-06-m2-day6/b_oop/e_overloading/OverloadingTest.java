package com.wanted.oop.b_oop.e_overloading;

public class OverloadingTest {

    /* comment. 메소드의 오버로딩
    *   메소드 시그니처란?
    *   메소드를 식별할 수 있는 고유한 값
    *   메소드명([매개변수])
    *   이 부분이 메소드의 시그니처라고 불리운다.
    * */

    // 오버로딩을 이용한 테스트 기준 메서드
    public void test() {}

    // 메소드의 시그니처가 동일하면 Error 발생
//    public void test() {}

    // 접근제한자를 변경해도 시그니처가 동일해서 Error 발생
//    private void test() {}

    // 반환타입을 변경해도 시그니처가 동일해서 Error 발생
//    private int test() {
//        return 0;
//    }

    // 매개변수 유무에 따른 오버로딩 성립
    public void test(int num) {}

    // 매개변수 명은 메소드 시그니처에 영향을 주지 않는다.
//    public void test(int num2) {}

    // 매개변수 개수는 메소드 시그니처에 영향을 미친다.
    public void test(int num, String str) {}

    // 매개변수의 순서에 따른 오버로딩 성립
    public void test(String str, int num) {}

    // 메소드 이름은 시그니처이기 때문에 Error 소멸
    public void test2() {}
}
