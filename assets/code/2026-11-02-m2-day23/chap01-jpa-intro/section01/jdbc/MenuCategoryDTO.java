package com.wanted.jpaintro.section01.jdbc;

import lombok.*;

/* comment.
 *   "메뉴 조회 시 카테고리 이름도 보여주세요" 라는 요구사항 때문에 새로 만든 클래스
 *   - JOIN 결과(메뉴 + 카테고리명)를 담을 객체가 기존에 없기 때문에
 *   - SQL 이 바뀔 때마다 이런 클래스가 계속 늘어나게 된다.
 *  */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenuCategoryDTO {

    private int menuCode;
    private String menuName;
    private int menuPrice;
    private String categoryName;
    private String orderableStatus;

}
