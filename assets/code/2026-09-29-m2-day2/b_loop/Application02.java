package com.wanted.b_loop;

public class Application02 {

    public static void main(String[] args) {

        /* comment.
        *   while 반복문
        *   while 은 조건식이 true 동안 반복 실행.
        *   형식
        *   while(조건식) { 실행코드, 증감식 }
        *   - 반복 횟수가 불확실하거나
        *   - 조건에 따라 종료해야 할 때 사용된다.
        *  */

        // 초기식
        int count = 0;

        while (count <= 5) {
            System.out.println("카운트 : " + count);
            count++;
        }

    }
}
