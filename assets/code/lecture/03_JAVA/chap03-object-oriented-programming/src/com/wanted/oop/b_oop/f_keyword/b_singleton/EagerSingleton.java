package com.wanted.oop.b_oop.f_keyword.b_singleton;

// 해당 클래스는 리모컨 클래스라고 생각해보자.
public class EagerSingleton {

    // 필드에 인스턴스 초기화
    private static EagerSingleton eager = new EagerSingleton();

    // 기본생성자 private
    private EagerSingleton() {}

    // 인스턴스를 반환하는 메서드
    public static EagerSingleton getInstance() {
        return eager;
    }

}
