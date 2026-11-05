package com.wanted.project.domain.menu.model.dao;

import com.wanted.project.domain.menu.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * [MenuRepository]
 * 역할 : Menu Entity 의 DB 접근을 담당한다. (구현체는 Spring Data JPA 가 만든다)
 * 책임 : - "무엇을 조회/저장/삭제할지" 만 선언한다. (SQL 실행은 Spring Data JPA 의 몫)
 *        - Entity 만 다룬다. DTO 변환, 트랜잭션, 규칙 판단은 Service / Policy 의 책임이다.
 *        - Menu 팀 소유. 다른 팀(Order 등)은 이 Repository 를 직접 사용하지 않는다.
 *
 * 할 일
 *  1. Menu Entity 를 관리하는 JPA Repository 로 만들기
 *  2. 쿼리 메서드 : 입력 가격 "초과" 메뉴를 가격 오름차순으로 조회
 *  3. [PART 3] 이름 중복 확인용 쿼리 메서드 (MenuNamePolicy 가 사용)
 *     - existsByMenuName()               : 같은 이름의 메뉴가 있는가?
 *     - existsByMenuNameAndMenuCodeNot() : "나를 제외하고" 같은 이름의 메뉴가 있는가? (이름 수정 시)
 */

/* comment.
*   extends JpaRepository<Menu, Integer>
*   JpaRepository 란? 영속성 컨텍스트와 엔티티 매니저를 구현한
*   구현체이다. 따라서 우리는 interface 로 dao 계층을 만들고
*   상속을 받게 되면 모든 기능을 사용할 수 있게 된다.
*   JpaRepository<관리할 엔티티, 해당 엔티티의 @Id 컬럼의 타입>
*  */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    // TODO 2
    /* comment. QueryMethod
    *   JPA 에서 제공해주는 기능으로, Entity 필드에 대해
    *   조건식, 그룹핑, 정렬 등을 메서드 형태로 작성할 수 있게
    *   해준다.
    *   장점 : 조건이 적다면 메서드로만 기능을 구현하기 편하다.
    *   단점 : 조건이 많아지거나, Join 이 일어나는 경우 가독성과
    *   정확한 메서드를 구현하기 힘들어진다.
    * */
    List<Menu> findByMenuPriceGreaterThanOrderByMenuPrice(int menuPrice);

    // TODO 3 : JPA 가 제공하는 쿼리 메서드
    boolean existsByMenuName(String menuName);
}
