package com.wanted.b_collection.b_set;

import java.util.Set;
import java.util.TreeSet;

public class Application02 {
    public static void main(String[] args) {

        /* comment. TreeSet
        *   TreeSet 을 활용한 로또 추첨기
        *   TreeSet 은 Set 처럼 중복을 허용하지 않는다.
        *   다만 HashSet 과의 차이는 이진 검색 트리 구조로 데이터의 정렬을 보장한다.
        *   이진 검색 트리 구조의 장점은 데이터를 순회하면서 조회 시 매우 빠르다는 장점이 있다.
        *   EX) 트리
        * */

        Set<Integer> lotto = new TreeSet<>();

        while (lotto.size() < 7) {
            // (int)(Math.random() * 45) + 1
            // Math.random() -> 0~1 사이의 난수 생성
            // * 45 -> 난수의 최댓값
            // + 1 -> 난수의 최솟값 (시작)
            lotto.add((int)(Math.random() * 45) + 1);
        }

        System.out.println("lotto = " + lotto);

    }
}
