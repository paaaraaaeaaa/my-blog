package com.wanted.persistencecontext.section02.lifecycle;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Component;

/* comment.
 *   엔티티의 생명주기
 *   - 비영속(new/transient) : 객체를 생성만 한 상태. 영속성 컨텍스트와 전혀 관련이 없다.
 *   - 영속(managed)        : 영속성 컨텍스트가 관리하는 상태. (persist, find 등)
 *   - 준영속(detached)      : 영속성 컨텍스트가 관리하다가 분리 된 상태. (detach, clear, close)
 *   - 삭제(removed)        : 영속성 컨텍스트와 DB 에서 삭제 될 상태. (remove)
 *   ※ 3 ~ 5번 예제는 데이터를 변경하기 때문에 실습 데이터를 지키기 위해 마지막에 rollback 한다.
 *  */
@Component
public class EntityLifeCycle {

    private final EntityManagerFactory factory;

    public EntityLifeCycle(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void transientState(int menuCode) {

        EntityManager manager = factory.createEntityManager();

        /* comment.
         *   객체를 생성하면(new), 영속성 컨텍스트와는 전혀 관련 없는 비영속 상태이다.
         *  */
        Menu foundMenu = manager.find(Menu.class, menuCode);

        if (foundMenu == null) {
            System.out.println(menuCode + "번 메뉴가 존재하지 않습니다.");
            manager.close();
            return;
        }

        Menu newMenu = new Menu();
        newMenu.setMenuCode(foundMenu.getMenuCode());
        newMenu.setMenuName(foundMenu.getMenuName());
        newMenu.setMenuPrice(foundMenu.getMenuPrice());
        newMenu.setCategoryCode(foundMenu.getCategoryCode());
        newMenu.setOrderableStatus(foundMenu.getOrderableStatus());
        // 자료형 같고, 값도 똑같음.

        System.out.println("foundMenu = " + foundMenu);
        System.out.println("newMenu   = " + newMenu);
        System.out.println("foundMenu == newMenu : " + (foundMenu == newMenu));
        System.out.println("foundMenu 는 영속 상태인가? " + manager.contains(foundMenu));
        System.out.println("newMenu 는 영속 상태인가? " + manager.contains(newMenu));

        manager.close();
    }

    public void managedState(int menuCode) {

        EntityManager manager = factory.createEntityManager();

        /* comment.
         *   find() 로 조회한 엔티티는 영속성 컨텍스트에 저장(1차 캐시)되어 관리되는 영속 상태이다.
         *   - 같은 PK 로 다시 조회하면 DB 에 SELECT 를 하지 않고 1차 캐시에 있는 엔티티를 반환한다.
         *   - 따라서 같은 엔티티 매니저 안에서는 동일성(==)이 보장된다.
         *  */
        System.out.println("===== 첫 번째 조회 =====");
        Menu foundMenu = manager.find(Menu.class, menuCode);
        System.out.println("===== 두 번째 조회 =====");
        Menu newMenu = manager.find(Menu.class, menuCode);

        if (foundMenu == null) {
            System.out.println(menuCode + "번 메뉴가 존재하지 않습니다.");
            manager.close();
            return;
        }

        System.out.println("foundMenu.hashCode() = " + foundMenu.hashCode());
        System.out.println("newMenu.hashCode()   = " + newMenu.hashCode());
        System.out.println("foundMenu == newMenu : " + (foundMenu == newMenu));

        manager.close();
    }

    public void detachedState(int menuCode1, int menuCode2) {

        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Menu foundMenu1 = manager.find(Menu.class, menuCode1);
        Menu foundMenu2 = manager.find(Menu.class, menuCode2);

        if (foundMenu1 == null || foundMenu2 == null) {
            System.out.println("존재하지 않는 메뉴 코드가 있습니다.");
            transaction.rollback();
            manager.close();
            return;
        }

        /* comment.
         *   detach() : 특정 엔티티를 영속성 컨텍스트에서 분리해 준영속 상태로 만든다.
         *   - 준영속 상태가 된 엔티티는 값을 변경해도 변경 감지가 동작하지 않는다.
         *   - clear() : 영속성 컨텍스트를 초기화해서 관리하던 모든 엔티티를 준영속 상태로 만든다.
         *   - close() : 영속성 컨텍스트를 종료해서 관리하던 모든 엔티티를 준영속 상태로 만든다.
         *  */
        manager.detach(foundMenu2);

        foundMenu1.setMenuPrice(foundMenu1.getMenuPrice() + 1000);
        foundMenu2.setMenuPrice(foundMenu2.getMenuPrice() + 1000);

        System.out.println(menuCode1 + "번 메뉴는 영속 상태인가? " + manager.contains(foundMenu1));
        System.out.println(menuCode2 + "번 메뉴는 영속 상태인가? " + manager.contains(foundMenu2));

        /* comment.
         *   flush() : 영속성 컨텍스트의 변경 내용을 DB 에 SQL 로 전달한다.
         *   flush 가 일어나면
         *   1. 변경 감지 : 영속 상태인 엔티티를 스냅샷과 비교한다.
         *   2. 변경 된 엔티티의 UPDATE 구문을 만들어 쓰기 지연 SQL 저장소에 등록한다.
         *   3. 쓰기 지연 SQL 저장소에 모아 둔 SQL(INSERT, UPDATE, DELETE)을 DB 로 전송한다.
         *   flush 가 호출되는 시점
         *   - transaction.commit() 을 호출할 때 (자동)
         *   - manager.flush() 를 직접 호출할 때
         *   - JPQL 쿼리를 실행하기 직전 (자동, JPQL 은 DB 에서 조회하므로 변경 내용을 먼저 반영한다.)
         *   주의!
         *   - flush 는 영속성 컨텍스트를 비우는 것이 아니다. (1차 캐시는 그대로 유지된다.)
         *   - flush 는 SQL 을 DB 에 전송할 뿐 commit 이 아니다.
         *   - 그래서 이 예제처럼 flush 후에 rollback 하면 전송 된 SQL 도 취소된다.
         *   확인해보기
         *   - 가격을 변경(setMenuPrice)한 코드는 "===== flush =====" 출력보다 먼저 실행되지만
         *   - 콘솔의 UPDATE 구문은 "===== flush =====" 아래에 출력된다. -> 쓰기 지연
         *   - 두 메뉴의 가격을 모두 변경했지만 영속 상태인 메뉴의 UPDATE 구문만 실행된다.
         *  */
        System.out.println("===== flush =====");
        manager.flush();

        transaction.rollback();
        manager.close();
    }

    public void removedState(int menuCode) {

        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Menu foundMenu = manager.find(Menu.class, menuCode);

        if (foundMenu == null) {
            System.out.println(menuCode + "번 메뉴가 존재하지 않습니다.");
            transaction.rollback();
            manager.close();
            return;
        }

        /* comment.
         *   remove() : 엔티티를 영속성 컨텍스트 및 DB 에서 삭제한다.
         *   단, 트랜젝션을 제어하지 않으면 영구 반영되지 않는다.
         *   - remove() 를 호출하면 엔티티는 즉시 삭제 상태가 되지만, DELETE 구문은 쓰기 지연 SQL 저장소에 등록된다.
         *   - 콘솔에서 DELETE 구문이 "===== flush =====" 아래에 출력되는 것을 확인하자. (쓰기 지연)
         *  */
        manager.remove(foundMenu);

        // 삭제 상태의 엔티티는 다시 조회해도 null 이 반환된다.
        Menu refoundMenu = manager.find(Menu.class, menuCode);

        System.out.println("삭제한 엔티티 객체 = " + foundMenu);
        System.out.println("다시 조회한 결과 = " + refoundMenu);
        System.out.println("삭제한 엔티티는 영속 상태인가? " + manager.contains(foundMenu));

        System.out.println("===== flush =====");
        manager.flush();

        transaction.rollback();
        manager.close();
    }

    public void merge(int menuCode, String menuName) {

        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Menu detachMenu = manager.find(Menu.class, menuCode);

        if (detachMenu == null) {
            System.out.println(menuCode + "번 메뉴가 존재하지 않습니다.");
            transaction.rollback();
            manager.close();
            return;
        }

        manager.detach(detachMenu);

        // 준영속 상태에서 값을 변경 -> 변경 감지 대상이 아니다.
        detachMenu.setMenuName(menuName);

        /* comment.
         *   merge() : 준영속 상태의 엔티티를 이용해서 새로운 영속 상태의 엔티티를 반환한다.
         *   1. 파라미터로 전달 된 엔티티의 PK 로 1차 캐시에서 엔티티를 찾는다. (없으면 DB 에서 SELECT)
         *   2. 찾아온 영속 엔티티에 준영속 엔티티의 값을 복사한다.
         *   3. 값이 복사 된 영속 엔티티를 반환한다. (파라미터로 전달한 객체가 영속 상태가 되는 것이 아니다!)
         *   ※ PK 로 조회 된 엔티티가 없다면 새로운 엔티티로 판단하여 저장(INSERT)한다.
         *  */
        System.out.println("===== merge =====");
        Menu mergedMenu = manager.merge(detachMenu);

        System.out.println("detachMenu.hashCode() = " + detachMenu.hashCode());
        System.out.println("mergedMenu.hashCode() = " + mergedMenu.hashCode());
        System.out.println("detachMenu == mergedMenu : " + (detachMenu == mergedMenu));
        System.out.println("detachMenu 는 영속 상태인가? " + manager.contains(detachMenu));
        System.out.println("mergedMenu 는 영속 상태인가? " + manager.contains(mergedMenu));
        System.out.println("mergedMenu.getMenuName() = " + mergedMenu.getMenuName());

        // 영속 상태인 mergedMenu 의 값이 바뀌었기 때문에 UPDATE 구문이 실행된다.
        System.out.println("===== flush =====");
        manager.flush();

        transaction.rollback();
        manager.close();
    }
}