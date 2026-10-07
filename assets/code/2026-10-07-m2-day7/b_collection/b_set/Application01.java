package com.wanted.b_collection.b_set;

import java.util.HashSet;
import java.util.Set;

public class Application01 {
    public static void main(String[] args) {

        /* comment. Set 자료구조 특징
        *   1. 요소의 저장 순서를 유지하지 않는다.
        *   2. 같은 요소의 중복 저장을 허용하지 않는다.
        * */

        // Set 인터페이스를 구현한 HashSet 을 가장 많이 쓴다.
        Set<String> hset = new HashSet<>();

        hset.add("java");
        hset.add("db");
        hset.add("servlet");
        hset.add("spring");
        hset.add("jpa");

        // Set 자료형은 중복된 요소는 허용하지 않는다.
        hset.add("jpa");

        System.out.println("hset = " + hset);

    }
}
