package com.wanted.a_controlflow;

import java.util.Scanner;

public class Application02 {

    public static void main(String[] args) {

        /* Scanner 와 if 문을 활용해서 프로그램 만들기 */
        /*
        * 나이가 13세 미만이면 청소년 50% 할인
        * 나이가 65세 이상이면 노약자 30% 할인
        *
        * input : 사용자는 나이를 입력한다.
        * output : 나이 : {사용자 입력 나이}, 할인율 : {나이별 할인율}%
        * */

        // 콘솔창에서 입력할 수 있게 만드는 Scanner
        Scanner sc = new Scanner(System.in);

        // 나이 입력 안내문 (ln : 한 줄 띄는 개행)
        System.out.print("나이를 입력해주세요 : ");

        // 입력한 나이를 저장할 변수
        // sc <- 스캐너, nextInt(); <- 사용자 입력 값을 int 로 받음
        int age = sc.nextInt();

        // 나이에 따라 달라질 할인율 변수 선언
        double discountRate;

        if(age < 13 ) {
            // 청소년 50%
            discountRate = 0.5;
        } else if (age >= 65) {
            // 노약자 30%
            discountRate = 0.3;
        } else {
            discountRate = 0.0;
        }

        System.out.println("나이 : " + age + ", 할인율 :" + (discountRate * 100) + "%");

    }
}
