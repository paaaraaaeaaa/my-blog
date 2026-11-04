package com.wanted.project.domain.menu.model.dto;

import com.wanted.project.domain.menu.model.entity.Menu;
import jakarta.validation.constraints.*;
import lombok.*;

/*
 * [MenuDTO]
 * 역할 : 화면(Controller) <-> Service 사이에서 메뉴 데이터를 주고받는 객체.
 * 책임 : - 화면에서 넘어온 값을 담고, 입력 "형식" 검증 규칙을 어노테이션으로 선언한다.
 *          (검증 실행은 Controller 의 @Valid 가, 실패 처리는 GlobalExceptionHandler 가 한다)
 *        - Entity -> DTO 변환을 책임진다. (Entity 는 DTO 를 몰라야 하므로 변환 메서드는 DTO 쪽에 둔다)
 *        - 비즈니스 로직이나 DB 접근은 하지 않는다.
 *
 *   DTO 검증 vs Entity 규칙
 *   - DTO    : 입력값 "자체" 만 보면 판단할 수 있는 형식 검증 (빈 값, 길이, 범위) -> @Valid
 *   - Entity : 저장된 "현재 상태" 에 따라 허용 여부가 달라지는 규칙 (이미 판매중지된 메뉴인가?)
 *              DTO 는 DB 에 저장된 현재 상태를 모르므로 검사할 수 없다.
 *
 * 할 일
 *  1. from() : Menu Entity -> MenuDTO 변환
 *  2. 메뉴 등록 시 검사할 검증 어노테이션 추가 (message 속성에 사용자에게 보여줄 문구 작성)
 *     - menuName     : 필수, 30자 이하 (tbl_menu.menu_name 이 VARCHAR(30))  -> @NotBlank, @Size
 *     - menuPrice    : 0 이상                                               -> @Min
 *     - categoryCode : 1 이상 (카테고리 미선택 방지)                         -> @Positive
 *     (orderableStatus 는 입력받지 않는다. 신규 메뉴는 항상 판매중(Y) 으로 시작 -> Menu.create() 의 책임)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenuDTO {

    private int menuCode;

    // TODO 2
    private String menuName;

    // TODO 2
    private int menuPrice;

    // TODO 2
    private int categoryCode;

    private String orderableStatus;

    public static MenuDTO from(Menu menu) {
        // TODO 1
        return new MenuDTO(
                menu.getMenuCode(),
                menu.getMenuName(),
                menu.getMenuPrice(),
                menu.getCategoryCode(),
                menu.getOrderableStatus()
        );
    }
}
