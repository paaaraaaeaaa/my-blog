package com.wanted.project.domain.menu.model.service;

import com.wanted.project.domain.menu.model.dao.CategoryRepository;
import com.wanted.project.domain.menu.model.dao.MenuRepository;
import com.wanted.project.domain.menu.model.dto.CategoryDTO;
import com.wanted.project.domain.menu.model.dto.MenuDTO;
import com.wanted.project.domain.menu.model.entity.Category;
import com.wanted.project.domain.menu.model.entity.Menu;
import com.wanted.project.domain.menu.model.policy.MenuCategoryPolicy;
import com.wanted.project.domain.menu.model.policy.MenuDeletionPolicy;
import com.wanted.project.domain.menu.model.policy.MenuNamePolicy;
import com.wanted.project.global.exception.BusinessException;
import com.wanted.project.global.exception.ErrorCode;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
 * [MenuService]
 * 역할 : 메뉴 관련 유스케이스(조회, 등록, 수정, 삭제)의 "흐름" 을 조립한다.
 * 책임 : - 트랜잭션 경계를 정한다. (등록/수정/삭제는 @Transactional)
 *        - 조회 -> 규칙 확인(Policy) -> 변경(Entity) -> 저장 -> DTO 변환 의 "순서" 만 책임진다.
 *        - 규칙을 직접 판단하지 않는다. (if 문으로 규칙을 검사하고 있다면 Entity 나 Policy 로 옮길 대상)
 *            · 도메인 규칙  (자기 값만으로 판단)        -> Entity
 *            · 비즈니스 규칙 (조회 / 다른 도메인이 필요) -> Policy
 *        - Controller 에는 DTO 만 돌려준다. (Entity 는 Service 밖으로 나가지 않는다)
 *        - 화면(View 이름, Model)은 모른다. (Controller 의 책임)
 *
 * 할 일
 *  0. 주입받기 : MenuRepository, CategoryRepository
 *               [PART 3] MenuNamePolicy, MenuCategoryPolicy, MenuDeletionPolicy
 *  1. findMenuByMenuCode() : 메뉴코드로 메뉴 1건 조회 (없으면 BusinessException(ErrorCode.MENU_NOT_FOUND))
 *  2. findMenuList()       : 전체 메뉴 목록 조회 (menuCode 오름차순 -> Sort.by)
 *  3. findMenuByPrice()    : 입력 가격 초과 메뉴 목록 조회
 *  4. findSubCategories()  : 하위 카테고리 목록 조회 (메뉴 등록 화면용)
 *  5. registNewMenu()      : 신규 메뉴 등록 후 생성된 menuCode 반환 (트랜잭션)
 *                            [PART 3] 등록 전 이름 중복(MenuNamePolicy), 하위 카테고리(MenuCategoryPolicy) 확인
 *  6. modifyMenuName()     : 메뉴 이름 수정 (트랜잭션, 없으면 MENU_NOT_FOUND)
 *                            [PART 3] 변경 전 이름 중복(MenuNamePolicy) 확인
 *  7. deleteMenu()         : 메뉴코드로 메뉴 삭제 (트랜잭션, 없으면 MENU_NOT_FOUND)
 *                            [PART 3] 삭제 전 판매중 여부(Menu.validateDeletable), 주문 이력(MenuDeletionPolicy) 확인
 *  8. [PART 3] stopSelling() / resumeSelling() : 메뉴 판매중지 / 판매재개 (트랜잭션, 없으면 MENU_NOT_FOUND)
 *                            상태 확인과 변경은 Menu 에게 맡긴다. (변경 감지로 UPDATE)
 */
@Service
@RequiredArgsConstructor
public class MenuService {

    // TODO 0 : DAO 계층 의존성 주입
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    // 메뉴 코드를 DAO 계층에 전달하여 메뉴 코드에 대한 엔티티를
    // 조회하라 명령을 내리며, Entity 객체를 DTO 로 변환하여
    // Controller 에 return
    public MenuDTO findMenuByMenuCode(int menuCode) {
        // TODO 1
        // findById() 메서드는 JPA 가 미리 구현해둔 메서드이다.
        // 해당 메서드의 반환 타입은 Optional<엔티티> 이다.
        // DB 에 menuCode 에 해당하는 데이터가 없으면 Error 가 발생하지만,
        // Optional 타입은 null 을 담게 되어 서비스 종료를 방지할 수 있다.
        Menu foundMenu = menuRepository.findById(menuCode)
                // 메뉴가 없다면 예외 발생!
                .orElseThrow(() -> new BusinessException(ErrorCode.MENU_NOT_FOUND));

        // Entity -> DTO 로 변환하는 from() 메서드
        MenuDTO menuDTO = MenuDTO.from(foundMenu);

        return menuDTO;
    }

    public List<MenuDTO> findMenuList() {
        // TODO 2
        return null;
    }

    public List<MenuDTO> findMenuByPrice(int menuPrice) {
        // TODO 3
        return null;
    }

    public List<CategoryDTO> findSubCategories() {
        // TODO 4 : 해당 메서드는 DB 에서 카테고리 데이터를 조회한다.
        List<Category> categoryList = categoryRepository.findSubCategory();

        return categoryList.stream()
                .map(CategoryDTO::from)
                .toList();
    }

    public int registNewMenu(MenuDTO registMenu) {
        // TODO 5
        return 0;
    }

    public void modifyMenuName(int menuCode, String menuName) {
        // TODO 6
    }

    public void deleteMenu(int menuCode) {
        // TODO 7
    }

    public void stopSelling(int menuCode) {
        // TODO 8
    }

    public void resumeSelling(int menuCode) {
        // TODO 8
    }

    public List<MenuDTO> findByMenuPrice(int menuPrice) {

        // 사용자가 입력한 menuPrice 이상인 메뉴 엔티티를 조회
        // 가격별 오름차순 정렬
        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThanOrderByMenuPrice(menuPrice);

        for(Menu menu : menuList) {
            System.out.println("menu = " + menu);
        }

        // Entity -> DTO
        // stream 문법 : for 문의 경우 코드가 많고, 복잡하다는 단점이 있다.
        // stream 은 그 단점을 짧은 코드로 해결할 수 있는 문법이다.
        List<MenuDTO> dtos =
                menuList.stream()
//                        .map(menu -> MenuDTO.from(menu))
                        .map(MenuDTO::from)
                        .toList();

        return dtos;
    }
}
