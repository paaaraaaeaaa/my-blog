package com.wanted.project.domain.menu.model.policy;

/*
 * [MenuOrderHistoryChecker] (제공 코드 - Menu 팀과 Order 팀이 합의한 "계약")
 * 역할 : 메뉴 삭제 정책이 필요로 하는 "주문 이력 여부" 를 묻는 인터페이스.
 * 책임 : - 필요한 쪽(Menu 팀)이 인터페이스를 정의하고, 정보를 가진 쪽(Order 팀)이 구현한다.
 *        - Menu 팀은 Order 팀의 Entity / Repository 를 전혀 모른다. 이 메서드 하나만 안다.
 *        - Order 는 메뉴 가격 때문에 Menu 에 의존하므로, Menu 가 Order 를 직접 의존하면 순환 의존이 생긴다.
 *          인터페이스를 Menu 쪽에 두면 의존 방향이 Order -> Menu 한 방향으로 유지된다. (의존성 역전)
 *
 * 값은 Entity 가 아닌 기본형(int, boolean) 으로 주고받는다. (다른 도메인은 ID 로 참조)
 */
public interface MenuOrderHistoryChecker {

    boolean hasOrdered(int menuCode);
}
