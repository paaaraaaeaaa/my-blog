package com.wanted.project.domain.menu.model.policy;

import com.wanted.project.global.exception.BusinessException;
import com.wanted.project.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
 * [MenuDeletionPolicy] - PART 3
 * 역할 : 비즈니스 규칙 "주문 이력이 있는 메뉴는 삭제할 수 없다" 를 판단한다.
 * 책임 : - 주문 이력은 다른 팀(Order) 의 데이터다. Order 팀의 Repository / Entity 를 직접 쓰지 않고,
 *          Menu 팀이 정의한 인터페이스(MenuOrderHistoryChecker) 에게 묻는다.
 *        - 실제 구현이 Fake 인지 진짜인지 이 클래스는 모른다. (스프링이 구현체를 주입한다)
 *
 * 할 일
 *  0. MenuOrderHistoryChecker 주입받기 (구현 클래스가 아닌 "인터페이스" 타입으로)
 *  1. validateDeletable() : 주문 이력이 있으면 BusinessException(ErrorCode.ORDERED_MENU_CANNOT_DELETE)
 */
@Component
@RequiredArgsConstructor
public class MenuDeletionPolicy {

    // TODO 0

    public void validateDeletable(int menuCode) {
        // TODO 1
    }
}
