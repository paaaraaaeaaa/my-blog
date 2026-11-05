package com.wanted.project.domain.order;

import com.wanted.project.domain.menu.model.policy.MenuOrderHistoryChecker;
import org.springframework.stereotype.Component;

import java.util.Set;

/*
 * [FakeMenuOrderHistoryChecker] (제공 코드 - Order 팀 영역)
 * 역할 : Order 팀의 실제 구현이 완성되기 전까지 사용하는 "가짜(Fake)" 구현체.
 * 책임 : - 계약(MenuOrderHistoryChecker)만 지키면 Menu 팀은 Order 팀을 기다리지 않고 개발 / 테스트할 수 있다.
 *        - 실습용으로 1, 2, 3번 메뉴는 주문 이력이 있다고 가정한다.
 *
 * Order 팀이 실제 구현(예: tbl_order_menu 를 조회하는 클래스)을 만들면 이 클래스는 삭제한다.
 * Menu 팀 코드(MenuDeletionPolicy)는 한 줄도 바뀌지 않는다. -> 인터페이스에 의존한 효과
 */
@Component
public class FakeMenuOrderHistoryChecker implements MenuOrderHistoryChecker {

    private static final Set<Integer> ORDERED_MENU_CODES = Set.of(1, 2, 3);

    /* comment.
    *   Menu 개발 팀은 Order 팀의 정보가 필요하다.
    *   따라서 메뉴 팀은 Interface 로 주문하고 주문 팀은 해당 Interface 를 구현하여 필요한 값을 전달한다.
    * */
    @Override
    public boolean hasOrdered(int menuCode) {
        return ORDERED_MENU_CODES.contains(menuCode);
    }
}
