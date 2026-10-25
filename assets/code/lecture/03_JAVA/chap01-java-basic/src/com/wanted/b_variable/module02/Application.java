package com.wanted.b_variable.module02;

public class Application {

    public static void main(String[] args) {
        
        /*comment. 형변환
        *   형변환(Type Conversion) 데이터 타입(자료형)을 다른 데이터 타입으로 변환하는 과정을 의미한다.
        *   형변환은 두 가지 방식이 있다.
        *   1. 암식적(묵시적) 형변환
        *   2. 명시적 형변환
        * */

        double dnum = 99.99;    // 8byte
        int inum = (int)dnum;   // 4byte
        // 디버깅용 출력 구문
        /*word. soutv*/
        System.out.println("dnum = " + dnum);
        System.out.println("inum = " + inum);

        // 암시적(묵시적) 형변환
        int num2 = 100;         // 4byte
        double dnum2 = num2;    // 8byte

        /* comment.
        *   명시적 형변환은 데이터 손실 가능성이 존재하기 때문에 컴파일러가 번역 시에 Error를 발생시킨다.
        *   데이터 손실을 감수하면서 형변환을 할 때는 명시적으로 작성을 해주어야만 한다.
        * */

    }
}
