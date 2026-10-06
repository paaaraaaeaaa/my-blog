package com.wanted.oop.d_polymorphism;

public class Raccoon extends Animal {

    @Override
    public void eat() {
        System.out.println("너구리가 너구리라면을 먹습니다...");
    }

    @Override
    public void run() {
        System.out.println("너구리가 쓰레기통으로 잽싸게 뛰어갑니다...");
    }

    @Override
    public void bark() {
        System.out.println("너굴너굴 너굴맨...");
    }

    public void bite() {
        System.out.println("분노한 너구리가 깨물기 시작합니다...");
    }
}
