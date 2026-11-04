package com.wanted.project.domain.menu.model.dto;

import com.wanted.project.domain.menu.model.entity.Category;
import lombok.*;

/*
 * [CategoryDTO]
 * 역할 : 화면(Controller) <-> Service 사이에서 카테고리 데이터를 주고받는 객체.
 * 책임 : - Category Entity -> CategoryDTO 변환을 책임진다.
 *        - @ResponseBody 로 반환되면 getter 를 통해 JSON 으로 변환된다.
 *        - 비즈니스 로직이나 DB 접근은 하지 않는다.
 *
 * 할 일
 *  1. from() : Category Entity -> CategoryDTO 변환
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDTO {

    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;

    public static CategoryDTO from(Category category) {
        // TODO 1 : Entity -> DTO
        return new CategoryDTO(
                category.getCategoryCode(),
                category.getCategoryName(),
                category.getRefCategoryCode()
        );
    }
}
