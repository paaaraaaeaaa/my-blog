package com.wanted.a_controlflow;

public class Application01 {

    // main() : Java 프로그램의 시작을 알리는 시발점
    // 항상 여기서부터 프로그램이 시작하게 된다.
    public static void main(String[] args) {

        System.out.println("프로그램이 시작됩니다.");
        /* comment. if 조건문
         *   if 문은 조건식의 결과에 따라서 프로그램의 실행 흐름을 "분기" 시키는 제어문이다.
         *   형식
         *   if(조건식) {조건 만족 시 실행 될 코드} [else {조건 불만족 시 실행될 코드} ]
         * */

        int score = 80;
        // 만약 점수가 90 이상이면 A 를 출력
        if (score >= 90) {
            System.out.println("A 등급입니다!");
        } else if (score >= 80) {
            // 90 미만 80 이상이면 B 를 출력
            System.out.println("B 등급입니다!");
        } else if (score >= 70) {
            // 80 미만 70 이상이면 C 를 출력
            System.out.println("C 등급입니다!");
        } else {
            // 그보다 낮으면 D 를 출력
            System.out.println("재수강 확정");
        }

        System.out.println("프로그램을 종료합니다.");
    }
}
