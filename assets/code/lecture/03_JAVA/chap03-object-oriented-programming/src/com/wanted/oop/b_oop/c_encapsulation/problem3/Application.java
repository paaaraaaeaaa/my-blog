package com.wanted.oop.b_oop.c_encapsulation.problem3;


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
        *   문제 1, 문제 2에서 발생하는 변수의 직접 접근과 잘못된 값 문제를 해결했다.
        *   Application 은 Monster 클래스의 변수에 직접 접근을 하지 않기 때문에 변수명을 변경해도 컴파일 에러 범위에서 벗어나게 되었다.
        *   ---
        *   다만 아직까지 문제는 완벽하게 해결되지 않았다.
        *   메서드로 접근하면 문제는 해결되지만, 아직까지 여전히 필드에 접근할 수 있다는 것이다.
        * */
        monster3.hp = -5500;
        System.out.println(monster3.getInfo());


    }
}
