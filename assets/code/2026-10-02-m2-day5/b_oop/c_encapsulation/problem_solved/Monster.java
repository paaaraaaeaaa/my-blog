package com.wanted.oop.b_oop.c_encapsulation.problem_solved;

public class Monster {

    /* comment.
    *   캡슐화 적용해서 최종 문제 해결!
    *   접근제한자를 활용해서 외부에서 필드에 접근을 막는다.
    * */

    private String kinds;
    private int hp;

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

    // 변수에 접근하는 것을 막기 위해 값을 세팅할 때 사용할 수 있는 메서드를 만들어본다.
    public void setName(String name) {
        this.kinds = name;
    }

    public String getInfo()  {
        return "몬스터의 이름은 " + this.kinds + "이고, " +
                "체력은 " + this.hp + "입니다.";
    }
}
