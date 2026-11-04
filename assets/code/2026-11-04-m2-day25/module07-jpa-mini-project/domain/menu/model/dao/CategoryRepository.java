package com.wanted.project.domain.menu.model.dao;

import com.wanted.project.domain.menu.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * [CategoryRepository]
 * 역할 : Category Entity 의 DB 접근을 담당한다.
 * 책임 : - 쿼리 메서드로 표현하기 어려운 조회는 @Query 로 직접 SQL 을 작성한다.
 *        - Entity 만 다룬다. DTO 변환은 Service 의 책임이다.
 *
 * 할 일
 *  1. Category Entity 를 관리하는 JPA Repository 로 만들기
 *  2. findSubCategories() : 하위 카테고리(ref_category_code 가 NULL 이 아닌 것)만 category_code 순으로 조회
 *                           (native query 직접 작성, 메뉴 등록 화면의 카테고리 목록에 사용)
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category , Integer>{

    // TODO 2 : 하위 카테고리 데이터를 find 하는 메소드
    // 쿼리메서드는 조건식이 복잡하거나, Join 이 많이 걸리면
    // 작성하기 힘들어진다.
    // @Query 어노테이션은 직접 SQL 구문을 작성할 수 있게 해준다.
    // 2개의 종류
    // 1. JPQL : 엔티티를 대상으로 SQL 구문 작성
    // 2. Native Query : 실제 사용 DB 의 SQL 구문을 작성
    // ---
    // 기본값은 jpql 이다.
    // nativeQuery = true 로 해야지 실제 SQL 을 작성할 수 있다.
    @Query(value = "select * from tbl_category m where ref_category_code is not null order by category_code", nativeQuery = true)
    List<Category> findSubCategory();
}
