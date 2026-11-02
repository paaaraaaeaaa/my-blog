package com.wanted.jpaintro.section02;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/* comment.
*   JPA 란?
*   자바 진영의 ORM (Object Relational Mapping) 기술 표준이다.
*   Java 객체를 Database 와 매핑한다.
* */
@Component
public class JpaSolution {

    /* comment.
     *   PersistenceContext 는 Entity 객체를 관리하는 컨테이너이다.
     *   Application 에서 만든 Entity 객체를 EntityManager 를 통해서
     *   PersistenceContext 에 넣을 수도 있고, 조회할 수도 있다.
     * */

    @PersistenceContext
    private EntityManager manager;

    // 사용자가 입력한 menuCode 로 DB 에 있는 menu 1개 조회
    public void findMenu(int menuCode) {

        // 엔티티 매니저가 관리하는 엔티티를 find (조회)
        /* comment.
        *   1. JDBC -> JPA 해결 포인트
        *   2. SQL 작성, JDBC 코드, DB 데이터 <-> Java 객체 매핑 코드
        *   위 3가지 내용은 JPA 를 사용했을 때 자동으로 수행해준다.
        * */
        Menu foundMenu = manager.find(Menu.class, menuCode);

        System.out.println(foundMenu);
    }


    /* comment.
    *   2. 2번 메뉴 조회 시 해당 메뉴의 모든 정보와 카테고리 정보를 조회.
    *   ex) 2, 파스타, 2000, Y, Category(1, 양식, 식사)
    *   데이터베이스와 Java 객체간의 패러다임 불일치 문제 해결
    * */
    public void findMenuAndCategory(int menuCode) {
        Menu foundMenu = manager.find(Menu.class, menuCode);
        System.out.println(foundMenu);
    }

    /* comment.
    *   3. 동일성 보장 문제 해결
    *   동일한 트렌젝션 내부에서 같은 PK 값으로 조회 한 엔티티는 항상 같은 객체를 반환한다.
    *   @Transactional 어노테이션을 메서드에 달게 되면 해당 메서드가 실행될 때 트렌젝션이 시작된다.
    *   또한 해당 메서드가 끝나게 되면 에러가 발생하지 않으면 commit / 에러가 발생하면 rollback 을 자동 처리해준다.
    *   단, readOnly 조건은 조회 전용 트렌젝션이다.
    * */
    @Transactional(readOnly = true)
    public void compareSameMenu(int menuCode) {

        System.out.println("===========1번째 조회===========");
        Menu menu1 = manager.find(Menu.class, menuCode);
        System.out.println("===========2번째 조회===========");
        Menu menu2 = manager.find(Menu.class, menuCode);

        System.out.println("menu1 = " + menu1.hashCode());
        System.out.println("menu2 = " + menu2.hashCode());
        System.out.println("menu1 == menu2 : " + (menu1 == menu2));

        // 1번 메뉴 이름 변경
        menu1.changeMenuName("테스트!");

        System.out.println("menu1 의 이름 변경 후!");
        System.out.println("menu1 = " + menu1.getMenuName());
        System.out.println("menu2 = " + menu2.getMenuName());
    }
}
