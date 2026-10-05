package com.wanted.oop.b_oop.c_encapsulation.problem2;

public class Monster {

    /* 내 프로그램에서 몬스터는 이름과 체력을 갖는다. */
//    String name;
//    int hp;

    /* 문제사항 정의 (2)
    *   요구사항 변경으로 인해 기존 name -> kinds
    * */
    String kinds;
    int hp;


    // 몬스터의 HP 를 설정하는 기능 메서드
    // hp 를 전달받아 양수인 경우 전달 받은 값으로 hp 세팅
    // 반면 음수인 경우 0으로 강제 변경
    public void setHp(int hp) {

        if (hp >= 0) {
            System.out.println("정상 값입니다. 몬스터의 체력을 " + hp + "로 설정합니다.");
            /* comment.
            *   this
            *   인스턴스(객체)가 생성될 때 자신의 주소를 저장하는 변수이다.
            *   지역변수와 전역변수의 이름이 같을 때, 지역변수를 우선적으로 접근하기 때문에 전역변수에 값을 대입하기 위해서는 this. 을 명시해야 된다.
            * */
            this.hp = hp;
        } else {
            System.out.println("삐빅... 오류 발생!" +"잘못된 값이 탐지되어 hp 를 0으로 강제합니다.");
            this.hp = 0;
        }

    }

}
