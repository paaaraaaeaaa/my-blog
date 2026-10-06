package com.wanted.oop.b_oop.f_keyword.b_singleton;

public class Application {

    public static void main(String[] args) {

        /* comment. static 키워드를 활용한 singleton 패턴
        *   싱글톤 -> 단일 인스턴스
        *   어플리케이션이 실행될 때 어떤 클래스가 최초 한 번만 메모리에 할당되고,
        *   그 메모리에 인스턴스를 만들어서 하나의 인스턴스를 공유해 사용하여 메모리 낭비를 방지할 수 있게 하는 디자인 패턴을 의미한다.
        *   - 리모콘 -> 1개
        * */

        /* comment. 싱글톤 패턴은 2가지 방법이 있다.
        *   1. 이른 초기화 (Eager)
        *   2. 게으른 초기화 (Lazy)
        *  */

        // 인스턴스를 생성하는 기본생성자를 private 로 막았기 때문에 외부 클래스에서는 new 로 객체를 만들 수 없게 만들어두었다.
//        EagerSingleton eager1 = new EagerSingleton();
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();

        System.out.println("eager1 의 hashcode() : " + eager1.hashCode());
        System.out.println("eager2 의 hashcode() : " + eager2.hashCode());

        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();

        System.out.println("lazy1 의 hashcode() : " + lazy1.hashCode());
        System.out.println("lazy2 의 hashcode() : " + lazy2.hashCode());

    }

}
