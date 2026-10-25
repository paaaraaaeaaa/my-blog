package com.wanted.oop.b_oop.c_encapsulation.problem1;


public class Application {
    public static void main(String[] args) {

        /* comment. 캡슐화 적용 전 발생할 수 있는 문제 */

        // 1번 몬스터 생성!
        Monster monster1 = new Monster();
        monster1.name = "성원몬";
        monster1.hp = 50;

        System.out.println("monster1.name = " + monster1.name);
        System.out.println("monster1.hp = " + monster1.hp);


        // 2번 몬스터 생성!
        Monster monster2 = new Monster();
        monster2.name = "피카츄!";
        // 문제 상황 발생 (1)
        // 검증되지 않은 값을 넣었을 때 문제가 발생할 수 있다.
        monster2.hp = -200;

        System.out.println("monster2.name = " + monster2.name);
        System.out.println("monster2.hp = " + monster2.hp);

        // 3번 몬스터 생성!
        Monster monster3 = new Monster();
        monster3.name = "갸라도스";
        monster3.setHp(-300);

        System.out.println("monster3.name = " + monster3.name);
        System.out.println("monster3.hp = " + monster3.hp);

    }
}
