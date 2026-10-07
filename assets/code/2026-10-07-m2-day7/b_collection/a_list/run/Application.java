package com.wanted.b_collection.a_list.run;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        /* comment. 컬렉션
        *   1. List
        *   - 순서가 있는 데이터의 집합. 중복을 허용한다.
        *   2. Set
        *   - 순서가 없는 데이터의 집합. 중복을 허용하지 않는다.
        *   3. Map
        *   - 키와 값 하나의 쌍으로 이루어지는 데이터 집합
        *   - key 값은 Set 으로 구현되어 있어 중복을 허용하지 않는다.
        * */

        // 인터페이스는 생성자를 작성할 수 없다.
        // == 즉 인터페이스 객체를 생성할 수 없다.
        // List 를 상속받은 클래스를 통해 객체를 생성한다.
        // ArrayList 는 List 를 상속받아 구현을 할 구현체이다.
        // 가장 많이 사용이 되며, 내부적으로 배열의 특징을 갖는다.
        List list = new ArrayList();

        list.add("apple");
        list.add(1);
        list.add(123.123);
        list.add(true);
        list.add(new Date());

        System.out.println("list = " + list);
        System.out.println("list.size() = " + list.size());

        System.out.println("1번 공간에 있는 값 = " + list.get(2));

        // 배열의 단점 : 고정크기, 기존 값 수정
        list.add(1,"banana");
        System.out.println("list = " + list);

        list.remove(2);
        System.out.println("list = " + list);

        System.out.println("=================================================");
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("c");
        strings.add("b");
        strings.add("d");
        System.out.println("strings = " + strings);
        Collections.sort(strings);
        System.out.println("strings = " + strings);

    }

}
