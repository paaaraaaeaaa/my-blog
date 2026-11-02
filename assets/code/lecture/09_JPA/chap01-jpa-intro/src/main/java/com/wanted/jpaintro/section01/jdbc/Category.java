package com.wanted.jpaintro.section01.jdbc;

import lombok.*;

/* comment.
 *   tbl_category 테이블의 한 행을 담기 위한 클래스
 *  */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category {

    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;

}
