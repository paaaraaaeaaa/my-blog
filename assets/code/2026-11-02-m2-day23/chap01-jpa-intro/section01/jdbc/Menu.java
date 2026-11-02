package com.wanted.jpaintro.section01.jdbc;

import lombok.*;

/* comment.
 *   tbl_menu 테이블의 한 행을 담기 위한 클래스
 *   - 테이블 구조에 맞춰서 만들었기 때문에
 *   - 카테고리를 객체(Category)가 아닌 categoryCode 값(FK)으로 가지고 있다.
 *  */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Menu {

    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;

}
