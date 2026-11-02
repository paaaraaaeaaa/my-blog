package com.wanted.persistencecontext.section01;

import jakarta.persistence.*;
import lombok.*;

/* comment.
*   @Entity : JPA 가 관리하는 객체라는 것을 명시
*   @Table : 해당 엔티티 객체와 매핑이 될 테이블 이름 지정.
* */
@Entity
@Table(name = "tbl_menu")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Menu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;

    public void changeMenuName(String s) {
        this.menuName = s;
    }
}
