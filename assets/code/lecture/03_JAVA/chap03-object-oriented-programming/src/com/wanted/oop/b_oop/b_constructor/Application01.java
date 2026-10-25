package com.wanted.oop.b_oop.b_constructor;

public class Application01 {

    public static void main(String[] args) {

        /* comment. 생성자 함수(메서드)
        *   생성자란?
        *   - Member member = new Member();
        *   - 우리는 지금까지 객체를 생성할 때 위 구문을 작성했다.
        *   Member(); 해당 구문은 실제로 생성자라고 하는 메서드를 호출하는 구문이다.
        *  */


        System.out.println("main() 시작됨!");
        // Member() 클래스명() : jdk 의 컴파일러가 자동으로 추가해주는 메서드이다.
       //Member member = new Member();
        Member member = new Member("user01", "pass01", "raccoon", 20, '남', new String[]{"탁구", "야구"});

        System.out.println("member = " + member);


        System.out.println("main() 종료됨!");

    }
}
