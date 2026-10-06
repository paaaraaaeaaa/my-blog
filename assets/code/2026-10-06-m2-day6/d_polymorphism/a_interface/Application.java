package com.wanted.oop.d_polymorphism.a_interface;

public class Application {

    public static void main(String[] args) {

        // 인터페이스는 구현체가 없다.
        // 즉 new 키워드로 객체를 생성할 수 없다는 의미이다.
//        Animal animal = new Animal();


        // 인터페이스는 해당 인터페이스를 상속받는 클래스를 통해 객체를 생성하게 된다.

        // 다형성이 적용됨.
        Animal animal = new Raccoon();
    }
}
