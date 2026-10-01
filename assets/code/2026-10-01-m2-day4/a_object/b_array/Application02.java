package com.wanted.oop.a_object.b_array;

import java.util.Scanner;

public class Application02 {
    public static void main(String[] args) {

        /* comment.
        *   여러명의 Java 시험점수 계산기
        *   1. 5명의 Java 점수를 Scanner 로 입력받는다.
        *   2. 입력받은 점수의 합계와 평균을 실수로 출력한다.
        *  */

        // 입력 받기 위한 Scanner
        Scanner sc = new Scanner(System.in);

        // 입력 받은 값을 저장하기 위한 배열을 생성
        int[] scores = new int[5];

        // 총 5번을 입력받아서 저장해야 함.
        // 반복
        for (int i = 0; i < scores.length; i++) {
            System.out.print((i + 1) + "번 째 학생의 java 점수를 입력해주세요 : ");
            scores[i] = sc.nextInt();
        }

        // scores 배열에 사용자 입력한 값들이 들어갔다!
        // 합계와 평균을 담을 변수를 선언
        double sum = 0;
        double avg = 0;

        for (int i = 0; i < scores.length; i++) {
//            sum = sum + scores[i];
            sum += scores[i];
        }

        // sum 변수에 5명의 java 점수 합계가 담기게 됨.
        avg = sum / scores.length;
        System.out.println("sum = " + sum);
        System.out.println("avg = " + avg);



    }
}
