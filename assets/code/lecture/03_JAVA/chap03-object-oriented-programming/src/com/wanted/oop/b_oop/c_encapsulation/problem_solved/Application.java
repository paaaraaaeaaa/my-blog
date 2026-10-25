package com.wanted.oop.b_oop.c_encapsulation.problem_solved;


public class Application {
    public static void main(String[] args) {

        /* comment. 캡슐화 적용 전 발생할 수 있는 문제 (3)
        *   앞서 발생한 문제 1, 2를 해결해보고 그럼에도 불구하고 발생할 수 있는 문제를 봐보자.
        * */

        // 1번 몬스터 생성!
        Monster monster1 = new Monster();
        monster1.setName("성원몬");
        monster1.setHp(50);

        System.out.println(monster1.getInfo());
        String result1 = monster1.getInfo();
        System.out.println("result1 = " + result1);

        // 2번 몬스터 생성!
        Monster monster2 = new Monster();
        monster2.setName("피카츄@");
        monster2.setHp(-200);
        monster2.getInfo();
        System.out.println(monster2.getInfo());


        // 3번 몬스터 생성!
        Monster monster3 = new Monster();
        monster3.setName("갸라도스");
        monster3.setHp(-300);
        monster3.getInfo();
        System.out.println(monster3.getInfo());

        /* comment.
        *   Monster 클래스 필드에 private 를 걸어두면 외부에서는 필드에 접근 자체를 거부한다.
        *   */
//        monster3.hp = -5500;
        System.out.println(monster3.getInfo());


    }
}
