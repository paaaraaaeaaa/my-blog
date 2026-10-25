package com.wanted.oop.b_oop.b_constructor;

import java.util.Arrays;

public class Member {


    String id;
    String pwd;
    String name;
    int age;
    char gender;
    String[] hobby;

    /* comment. 생성자 표현식
    *   접근제한자 클래스명([매개변수]) {}
    *   접근제한자 반환타입 메서드명([매개변수])
    *   생성자는 객체가 최초에 생성되는 시점 (==new 를 만나는 시점) 에 가장 먼저 동작하는 메서드이다.
    *   사용 목적
    *   1. 객체 생성 시점에 수행 할 명령이 있는 경우
    *   2. 생성 시점에 변수(필드) 초기화
    *   - 매개변수가 없는 생성자는 기본값으로 변수 초기화
    *   - 매개변수가 있는 생성자는 전달인자로 변수 초기화
    *   주의사항
    *   - 클래스 내부에 매개변수가 있는 생성자를 작성한다면 컴파일러는 기본 생성자를 자동으로 생성하지 않는다.
    * */

    public Member() {
        System.out.println("기본생성자 동작함!");
    }

    public Member(String id, String pwd, String name, int age, char gender, String[] hobby) {
        System.out.println("매개변수 있는 생성자 동작함!");
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", hobby=" + Arrays.toString(hobby) +
                '}';
    }
}
