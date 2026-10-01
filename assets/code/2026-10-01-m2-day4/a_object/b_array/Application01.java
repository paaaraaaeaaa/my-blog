package com.wanted.oop.a_object.b_array;

public class Application01 {
    public static void main(String[] args) {

        // 문제사항
        // 1,2,3,4,5 를 변수에 담아 하나씩 더해서 sum 변수에 담아라
        int num1 = 1;
        int num2 = 2;
        int num3 = 3;
        int num4 = 4;
        int num5 = 5;

        int sum = num1 + num2 + num3 + num4 + num5;

        /* comment.
        *   변수의 한계
        *   - 변수는 1개의 값이 들어갈 수 있는 공간이다.
        *   - 따라서 여러 개의 값을 저장해야 한다면 그에 맞추어 여러 개의 변수를 생성해야 한다.
        *   배열
        *   - 동일한 자료형의 묶음
        *   - 핵심 "동일한 자료형"
        *   형식
        *   - 자료형[] 변수명; : 선언
        *   - int[] iarr;
        *   - new 자료형[크기]; : 할당
        * */

        int[] iarr = new int[5];

        System.out.println("iarr = " + iarr);
        System.out.println("iarr.length = " + iarr.length);

        // 각 공간에 들어있는 값을 출력하는 방법
        // 변수명[index 번호]
        System.out.println(iarr[0]);

        /* comment.
        *   우리는 배열의 값을 넣은 적이 없다.
        *   근데 0 번째 공간을 출력했을 때 0 이라는 값이 출력되고 있다.
        *   - heap 메모리 영역의 특징
        *   - heap 공간은 비어있는 값이 존재할 수 없다.
        *   따라서 우리가 값을 넣지 않더라도 jvm 이 지정한 기본값들로 셋팅이 된다.
        *   - 정수 : 0 / 실수 : 0.0 / 논리 : false / 참조 : null
        * */

        // 10개의 정수가 들어갈 수 있는 배열 생성
        int[] iarr2 = new int[10] ;

        // 배열의 장점
        // index 번호 체계를 가진다. 1씩 증가한다.
        // 반복문을 사용할 때 유용하다.
        for(int i = 0; i < iarr2.length; i++) {
            System.out.println("iarr[" + i + "] 공간에 있는 값 " + iarr2[i]);
        }

    }
}
