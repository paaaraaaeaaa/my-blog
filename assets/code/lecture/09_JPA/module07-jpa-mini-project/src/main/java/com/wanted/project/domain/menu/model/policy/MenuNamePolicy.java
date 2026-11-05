package com.wanted.project.domain.menu.model.policy;

import com.wanted.project.domain.menu.model.dao.MenuRepository;
import com.wanted.project.global.exception.BusinessException;
import com.wanted.project.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
 * [MenuNamePolicy] - PART 3
 * 역할 : 비즈니스 규칙 "메뉴 이름은 중복될 수 없다" 를 판단한다.
 * 책임 : - 다른 메뉴들을 조회해야 판단할 수 있는 규칙이므로 Entity 가 아닌 Policy 가 맡는다.
 *        - "된다 / 안 된다" 만 판단한다. 위반 시 BusinessException 을 던지고, 통과하면 아무것도 하지 않는다.
 *        - 상태를 바꾸지 않는다. (save 호출, Entity 값 변경, DTO 반환 금지 -> Service 의 책임)
 *
 * 할 일
 *  0. MenuRepository 주입받기
 *  1. validateNewName() : 같은 이름의 메뉴가 있으면 BusinessException(ErrorCode.DUPLICATE_MENU_NAME)
 *  2. validateRename()  : "자기 자신을 제외하고" 같은 이름의 메뉴가 있으면 DUPLICATE_MENU_NAME
 *                         (자기 이름 그대로 저장하는 경우는 중복이 아니다)
 */
@Component
@RequiredArgsConstructor
public class MenuNamePolicy {

    // TODO 0 : JPA 를 상속받은 Interface 의존
    private final MenuRepository menuRepository;
    /* comment.
    *   한계점.
    *   동시에 2명이 같은 이름을 가진 메뉴를 등록할 때는 둘 다 통과할 수 있다. (조회/저장 사이의 간격)
    *   따라서 실제 운영환경에서는 menu_name 컬럼에 unique 제약조건까지 붙여야 완벽하게 의도된대로 동작하게 된다.
    * */

    public void validateNewName(String menuName) {
        // TODO 1 : DB 를 거쳐서 중복된 메뉴이름이 존재하는 지를 확인
        if (menuRepository.existsByMenuName(menuName)) {
            // 해당 if 문 내부는 중복된 메뉴가 있을 때 동작
            throw new BusinessException(ErrorCode.DUPLICATE_MENU_NAME);
        }
    }

    public void validateRename(int menuCode, String newMenuName) {
        // TODO 2
    }
}
