package com.wanted.oop.b_oop.a_uesr_type;

public class Application01 {
    public static void main(String[] args) {

        /* comment. 사용자 정의의 자료형
        *   지금까지는 Java 에서 제공하는 자료형만 사용했다.
        *   ex) int, char, ... int[], String[]
        *   - 회원 정보를 관리하는 프로그램
        *   - 회원 : 아이디, 패스워드, 이름, 나이, 성별, 취미
        * */

        String id = "user01";
        String pwd = "pass01";
        String name = "raccoon";
        int age = 20;
        char gender = '남';
        String[] hobby = {"괴롭히기", "웃기", "야구 하이라이트 시청"};

        /* comment. 문제 정의
        *   1명 회원의 정보를 1개의 변수로 묶고 싶은데 자료형이 다르기 때문에 배열과 기본 자료형을 사용해서는 묶을 수 있는 방법이 없다.
        *   단점
        *   1. 변수명을 전부 관리해야 한다.
        *   2. 모든 회원의 정보를 메소드 호출 시 인자로 전달하고자 하면 전달인자와 매개변수가 너무 비대해진다.
        *   3. 메소드의 return 시 1개의 자료형만 return 할 수 있기 때문에 회원 정보를 묶어서 return 할 수 없다.
        */

        // 클래스명 변수명 = new 클래스명();
        Member member = new Member();
        System.out.println("member 의 이름 : " + member.name);
        System.out.println("member 의 나이  : " + member.age);

        // 필드에 접근해서 값을 초기화 해보기
        member.id = "user02";
        member.hobby = new String[] {"야구시청", "배드민턴"};

        /* comment.
        *   사용자 정의의 가료형이란 기존 자료형의 한계를 극복하여 서로 다른 자료형들을 하나의 변수르 묶을 수 있다.
        * */
        System.out.println("member.id = " + member.id);


    }
}
