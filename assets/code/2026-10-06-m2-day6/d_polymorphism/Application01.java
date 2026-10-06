package com.wanted.oop.d_polymorphism;

public class Application01 {

    /* comment. 다형성이란?
    *   하나의 인스턴스가 여러가지 타입을 가질 수 있는 것을 의미한다.
    *   그렇기 때문에 하나의 타입으로 여러 타입의 인스턴스를 처리할 수 있고, 하나의 메소드 호출로 객체 별 다른 방법으로 동작하게 할 수 있다.
    *  */


    public static void main(String[] args) {

        System.out.println("==============Animal==============");
        Animal animal = new Animal();
        animal.eat();
        animal.run();
        animal.bark();
        System.out.println("==============Animal==============");

        System.out.println("==============Raccoon==============");
        Raccoon raccoon = new Raccoon();
        raccoon.eat();
        raccoon.run();
        raccoon.bark();
        raccoon.bite();
        System.out.println("==============Raccoon==============");

        System.out.println("==============Koala==============");
        Koala koala = new Koala();
        koala.eat();
        koala.run();
        koala.bark();
        koala.sleep();
        System.out.println("==============Koala==============");

        /* comment.
        *   IS-A 관계
        *   너구리는 동물이다 (O)
        *   raccoon = animal -> x
        *   animal = raccoon -> O
        *   동물은 너구리다 (X)
        *  */

        Animal a1 = new Raccoon();


        /* comment.
        *   동적 바인딩.
        *   컴파일 시점에는 Animal 타입의 메소드와 연결이 되어 있다가,
        *   런타임 시점에 실제 인스턴스(Raccoon)가 가진 오버라이딩 된 메서드로 변경되어 동작하는 것을 의미한다.
        * */
        a1.bark();
        // 컴파일 시점에 a1 은 Animal 타입이기 때문에 Raccoon 의 고유 기능은 사용 불가능하다.
//        a1.bite();

        // 클래스 형변환
        // 부모 자식 관계에서 사용 가능하다.
        ((Raccoon) a1).bite();

        // 애니멀 값은 라쿤 공간에 들어갈 수 없다.
//        Raccoon r1 = new Animal();

    }

}
