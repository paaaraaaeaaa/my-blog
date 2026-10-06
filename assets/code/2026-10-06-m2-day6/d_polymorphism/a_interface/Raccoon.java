package com.wanted.oop.d_polymorphism.a_interface;

// 인터페이스를 상속받을 때는 extends 가 아닌 implements 로 하게 된다.
public class Raccoon implements Animal{
    @Override
    public void run() {
        System.out.println("너구리가 폴짝폴짝 뛰어댕깁니다..");
    }

    @Override
    public void eat() {

    }

    @Override
    public void bark() {

    }
}
