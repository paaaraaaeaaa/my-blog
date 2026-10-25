package com.wanted.a_exception.a_basic;

public class Application {
    public static void main(String[] args) {

        System.out.println("프로그램 시작...");

        /* comment.
        *   컴파일 오류
        *   - 컴파일 오류란, 개발자가 코드를 작성 중에 발생하는 오류를 의미한다.
        *   ex) 없는 변수 참조, 타입 불일치
        *   런타임 오류
        *   - 어플리케이션 실행 시 코들르 실행하면서 발생하는 오류이다.
        *   대표적인 에러는 NullPointerException (null 참조)
        * */

        int[] iarr = new int[5];
        System.out.println("6번째 인덱스 출력 : " + iarr[6]);

        String str = null;
        str.length();

        /* comment.
        *   예외에 대한 처리를 하지 않으면 프로그램 실행 중 발생한 예외로 인해 프로그램이 비정상적으로 종료될 수 있다.
        * */



        System.out.println("프로그램 종료...");

    }
}
