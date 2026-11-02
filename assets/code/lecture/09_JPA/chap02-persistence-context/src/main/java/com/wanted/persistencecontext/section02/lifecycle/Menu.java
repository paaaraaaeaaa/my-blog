package com.wanted.persistencecontext.section02.lifecycle;

import jakarta.persistence.*;
import lombok.*;

/* comment.
 *   @Entity
 *   - JPA 가 관리하는 엔티티 클래스라는 것을 표시한다.
 *   - 엔티티 이름은 기본값으로 클래스 이름(Menu)을 사용하며, @Entity(name = "...") 으로 변경할 수 있다.
 *   - 같은 이름의 엔티티가 2개 이상 등록되면 충돌이 나는데
 *   - 이 프로젝트는 섹션마다 실행 클래스가 해당 섹션 패키지만 스캔하기 때문에 충돌하지 않는다.
 *  */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
// 데이터베이스에 매핑이 될 테이블 이름 설정
@Table(name = "tbl_menu")
public class Menu {

    @Id // PK 의 역할
    @Column(name = "menu_code") // 데이터베이스에 적용이 될 컬럼명
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;

}