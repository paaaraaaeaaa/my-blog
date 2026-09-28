package com.wanted.b_variable.module01;

public class Application {

    public static void main(String[] args) {
        /*comment. 리터럴 이란?
        *   - 리터럴은 "값"을 의미한다.
        *   - 리터럴은 순수한 값으로서 정수, 실수, 문자, 문자열, 논리 등으로 구성되어 있다.
        * */

        //숫자형(정수, 실수)
        /*word. b,s */

        // 숫자형 - 정수를 담는 자료형
        // 변수는? 값을 저장하는 공간!
        // 자료형 변수(공간) =(대입연산자) 연산자 리터럴(값) 순서대로.
        byte b = 10;    // 1 byte
        short s = 10;   // 2 byte
        int i = 10;     // 4 byte
        long l = 10;    // 8 byte

        // 숫자형 - 실수를 담는 자료형
        float f = 3.14f;    // 4 byte
        double d = 3.14;    // 8 byte

        // 문자형
        char c = 'ㄴ'; // " 안돼요! 그리고 한 글자만 가능.

        // 문자열
        String str = "안녕하세요!"; // ' 안돼요! 여러 글자 가능.
        // 같은 영역 ({}) 내부에서 동일한 변수명을 사용하는 것은 불가능하다.
        /* word. ctrl+d = 복붙 */
        String str2 = "안녕하세요!"; // ' 안돼요! 여러 글자 가능.

        // 논리형 - 참/거짓
        boolean bl = true;
        boolean bl2 = false;

        /* comment.
        *   변수의 선언과 초기화
        * */
        // 변수의 선언
        int num;
        // 변수의 초기화
        num = 30;
        // 변수의 선언과 동시에 초기화
        int num2 = 10;


    }
}
