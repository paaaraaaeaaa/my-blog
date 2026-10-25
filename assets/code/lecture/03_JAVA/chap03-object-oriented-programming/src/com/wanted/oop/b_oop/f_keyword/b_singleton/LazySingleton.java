package com.wanted.oop.b_oop.f_keyword.b_singleton;

public class LazySingleton {

    private static LazySingleton lazy;

    private LazySingleton() {}

    // 외부에서 객체 필요 시 호출하는 메소드
    public static LazySingleton getInstance() {

        if (lazy == null) {
            lazy = new LazySingleton();
        }
        return lazy;

    }

}
