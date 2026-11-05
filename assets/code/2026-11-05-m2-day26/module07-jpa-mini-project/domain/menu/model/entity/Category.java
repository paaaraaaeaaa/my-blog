package com.wanted.project.domain.menu.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * [Category - Entity]
 * 역할 : tbl_category 테이블 한 행과 매핑되는 도메인 객체.
 * 책임 : - 테이블 / 컬럼 매핑 정보를 가진다.
 *        - 자기 값으로 답할 수 있는 질문(하위 카테고리인가?)에 스스로 답한다.
 *          (다른 클래스가 getRefCategoryCode() == null 같은 내부 구조를 직접 해석하지 않게 한다)
 *        - 조회 전용으로 사용하므로 값을 바꾸는 메서드(setter)를 열지 않는다.
 *
 * 할 일
 *  1. 클래스에 엔티티 / 테이블(tbl_category) 매핑
 *  2. 각 필드를 컬럼에 매핑 (category_code 가 PK)
 *  3. [PART 3] isSubCategory() : 상위 카테고리가 있으면(refCategoryCode 가 null 이 아니면) 하위 카테고리
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "tbl_category")
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;    // 최상위 카테고리는 NULL 이므로 int 가 아닌 Integer

    public boolean isSubCategory() {
        // TODO 3 : 도메인의 상태를 물어보는 메서드
        return refCategoryCode != null;
    }
}
