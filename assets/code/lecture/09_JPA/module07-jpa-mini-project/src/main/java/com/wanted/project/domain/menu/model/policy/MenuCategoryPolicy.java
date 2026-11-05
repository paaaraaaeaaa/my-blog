package com.wanted.project.domain.menu.model.policy;

import com.wanted.project.domain.menu.model.dao.CategoryRepository;
import com.wanted.project.domain.menu.model.entity.Category;
import com.wanted.project.global.exception.BusinessException;
import com.wanted.project.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
 * [MenuCategoryPolicy] - PART 3
 * 역할 : 비즈니스 규칙 "메뉴는 하위 카테고리에만 등록할 수 있다" 를 판단한다.
 * 책임 : - 카테고리를 조회해야 판단할 수 있으므로 Policy 가 맡는다.
 *        - "하위 카테고리인가?" 라는 질문 자체는 Category 가 스스로 답한다. (Category.isSubCategory())
 *          -> Policy 는 Entity 의 내부 구조(refCategoryCode)를 해석하지 않고, Entity 에게 묻는다.
 *        - 화면에서 하위 카테고리만 보여주더라도 서버에서 반드시 다시 검증한다.
 *          (화면 제한은 편의 기능일 뿐, 요청은 화면을 거치지 않고도 보낼 수 있다)
 *
 * 할 일
 *  0. CategoryRepository 주입받기
 *  1. validateRegistrable() : 카테고리가 없으면 BusinessException(ErrorCode.CATEGORY_NOT_FOUND),
 *                             하위 카테고리가 아니면 BusinessException(ErrorCode.NOT_SUB_CATEGORY)
 */
@Component
@RequiredArgsConstructor
public class MenuCategoryPolicy {

    // TODO 0
    private final CategoryRepository categoryRepository;

    public void validateRegistrable(int categoryCode) {
        // TODO 1 : 카테고리 DB 를 조회해서 하위 카테고리인지 판단
        Category category = categoryRepository.findById(categoryCode)
                .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));

        // 조회한 카테고리 엔티티가 상위카테고리 / 하위카테고리 인지 판단
        if (!category.isSubCategory()) {
            throw new BusinessException(ErrorCode.NOT_SUB_CATEGORY);
        }
    }
}
