package com.wanted.project.domain.menu.model.entity;

import com.wanted.project.global.exception.BusinessException;
import com.wanted.project.global.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.*;

/*
 * [Menu - Entity]
 * 역할 : tbl_menu 테이블 한 행과 매핑되는 도메인 객체.
 * 책임 : - 메뉴를 "만드는 규칙" 과 "바꾸는 규칙" 을 스스로 책임진다.
 *          (전체 필드 생성자는 private, setter 없음 -> 이름 있는 메서드로만 생성/변경)
 *        - 도메인 규칙 : 자신의 "현재 상태" 에 따라 허용 여부가 달라지는 규칙을 판단한다.
 *          (예: 이미 판매중지된 메뉴는 다시 판매중지할 수 없다)
 *          DTO 는 화면에서 넘어온 값만 알고 DB 에 저장된 현재 상태는 모르기 때문에 @Valid 로는 검사할 수 없다.
 *        - Repository 나 다른 Bean 을 주입받지 않는다. (DB 조회가 필요한 규칙은 Policy 의 책임)
 *        - DTO, 화면을 알지 못한다. (파라미터로 DTO 가 아닌 "값" 을 받는다)
 *
 * 할 일
 *  1. 클래스에 엔티티 / 테이블(tbl_menu) 매핑
 *  2. 각 필드를 컬럼에 매핑 (menu_code 는 PK 이며 DB 가 자동 증가)
 *  3. create()          : 신규 메뉴 Entity 생성 (menuCode 는 받지 않는다, 판매상태는 항상 "Y" 로 시작)
 *  4. changeMenuName()  : 메뉴 이름 변경 (setter 없이 이 메서드로만 값을 바꾼다)
 *  5. [PART 3] 판매 상태 도메인 규칙
 *     - stopSelling()       : 판매중지 (이미 판매중지면 BusinessException(ErrorCode.ALREADY_STOPPED_MENU))
 *     - resumeSelling()     : 판매재개 (이미 판매중이면 BusinessException(ErrorCode.ALREADY_ON_SALE_MENU))
 *     - validateDeletable() : 판매중인 메뉴면 BusinessException(ErrorCode.ON_SALE_MENU_CANNOT_DELETE)
 */
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Entity
@Table(name = "tbl_menu")
public class Menu {

    @Id
    @Column(name = "menu_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;

    public static Menu create(String menuName, int menuPrice, int categoryCode) {
        // TODO 3
        return null;
    }

    public void changeMenuName(String newMenuName) {
        // TODO 4
    }

    public void stopSelling() {
        // TODO 5
    }

    public void resumeSelling() {
        // TODO 5
    }

    public void validateDeletable() {
        // TODO 5
    }
}
