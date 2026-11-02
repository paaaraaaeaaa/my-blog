package com.wanted.persistencecontext.section01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class EntityManagerCRUD {

    /* comment.
    *   엔티티 매니저는 기능 1개 당 1개의 매니저를 별도로 생성해야 한다.
    *   즉 필드에서 매니저를 공유하는 것이 아닌, 1개의 요청 시 1개의 매니저만 존재해야 한다.
    * */
//    @PersistenceContext
//    private EntityManager manager;
    private final EntityManagerFactory factory;

    public EntityManagerCRUD(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void findMenuByMenuCode(int menuCode) {
        // 조회 : find(엔티티 타입, PK 값)

        // manager 는 1회 요청 시 인스턴스를 생성하며 요청이 종료되면 없앤다.
        EntityManager manager = factory.createEntityManager();

        Menu foundMenu = manager.find(Menu.class, menuCode);
        System.out.println("foundMenu = " + foundMenu);
        manager.close();
    }

    /* comment.
    *   Create : 엔티티 객체를 Persistence Context 에 등록
    *   persist();
    * */
    public void registMenu(Menu menu) {

        EntityManager manager = factory.createEntityManager();
        // DML (Insert, Update, Delete) 의 경우 트렌젝션이 매우 중요하다.
        // DML 중 오류가 발생하면 롤백이 반드시 필요하다.
        EntityTransaction transaction = manager.getTransaction();
        // 해당 코드 아래부터는 트렌젝션이 적용된다.
        transaction.begin();
        // 영속성 컨텍스트에 저장하는 메서드
        try {
            // 엔티티 매니저는 CUD 작업 시 SQL 을 DB 에 바로 반영하지 않는다.
            // "쓰기 지연 SQL 저장소" 에 SQL 을 모아두었다가 flush() 시점에 DB 로 SQL 을 보내준다.
            manager.persist(menu);  // 영속성 컨텍스트에 집어넣기
            // commit() 메서드가 호출되면 flush 가 자동 실행된다.
            transaction.commit();   // db 반영
            System.out.println("등록 된 메뉴 : " + menu);
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            manager.close();
        }
    }

    /* comment.
    *   Update : update 관련 메서드는 존재하지 않는다.
    *   - 영속성 컨텍스트는 엔티티를 처음 조회(find) 하는 순간 엔티티의 상태(스냅샷)을 보관하고 있다.
    *   - 보관 된 스냅샷과 commit 시점의 엔티티를 비교해서 변경 된 부분이 있다면 UPDATE 쿼리문을 만들어서 쓰기 지연 저장소에 등록 후 DB 에 전송한다.
    *   - 위 과정을 변경 감지(Dirty Checking) 라고 한다.
    * */
    public void modifyMenuName(int menuCode, String menuName) {

        EntityManager manager = factory.createEntityManager();
        Menu foundMenu = manager.find(Menu.class, menuCode);
        System.out.println("수정 전 메뉴 : " + foundMenu);
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        try {
            // setter 메서드를 사용해서 값 변경
            foundMenu.setMenuName(menuName);
            System.out.println("==========commit 하기 전=========");
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        System.out.println("수정 후 메뉴 : " + manager.find(Menu.class, menuCode));
        manager.close();
    }

    /* comment.
    *   Delete : remove(엔티티);
    *   - 삭제 할 엔티티는 먼저 조회 후 영속성 컨텍스트가 관리하고 있는 상태에서 삭제가 가능하다.
    *   - remove(); 메서드를 호출해도 commit() 이 되는 시점에 delete 쿼리문이 DB 로 전송된다.
    * */
    public void removeMenu(int menuCode) {

        EntityManager manager = factory.createEntityManager();
        Menu reomveMenu = manager.find(Menu.class, menuCode);

        // DML 수행 전 트랜젝션 시작!
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        try {
            manager.remove(reomveMenu);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        Menu removedMenu = manager.find(Menu.class, menuCode);

        manager.close();
    }
}
