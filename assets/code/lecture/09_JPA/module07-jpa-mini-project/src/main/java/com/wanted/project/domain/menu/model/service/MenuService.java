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

    // TODO 5 : Service 는 규칙을 판단하지 않고, Policy 에게 위임힌다.
    private final MenuNamePolicy menuNamePolicy;
    private final MenuCategoryPolicy menuCategoryPolicy;
    private final MenuDeletionPolicy menuDeletionPolicy;

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
        // findAll(Sort) 는 JpaRepository 가 기본으로 제공한다. menuCode 오름차순으로 정렬한다.
        List<Menu> menuList = menuRepository.findAll(Sort.by("menuCode"));

        return menuList.stream()
                .map(MenuDTO::from)
                .toList();
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

    /* ================================================================================================
     * TODO 5 : 메뉴 등록
     * ------------------------------------------------------------------------------------------------
     * [Before] PART 3 · STEP 8 - Service 가 비즈니스 규칙을 직접 판단한다.
     *          (이름 1~30자, 가격 0원 이상 같은 "입력 형식" 은 MenuDTO 의 @Valid 가 이미 막았다)
     *
     *     @Transactional
     *     public int registNewMenu(MenuDTO registMenu) {
     *
     *         // ① 비즈니스 규칙 : 이름 중복 금지 (다른 메뉴를 조회해야 판단 가능)
     *         if (menuRepository.existsByMenuName(registMenu.getMenuName())) {
     *             throw new BusinessException(ErrorCode.DUPLICATE_MENU_NAME);
     *         }
     *
     *         // ② 비즈니스 규칙 : 하위 카테고리에만 등록 (카테고리를 조회해야 판단 가능)
     *         Category category = categoryRepository.findById(registMenu.getCategoryCode())
     *                 .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));
     *         if (category.getRefCategoryCode() == null) {           // Entity 내부 구조를 Service 가 해석
     *             throw new BusinessException(ErrorCode.NOT_SUB_CATEGORY);
     *         }
     *
     *         // ③ 흐름 : 생성 -> 저장 -> 반환  (정작 이 메서드의 "할 일" 은 여기 3줄뿐이다)
     *         Menu menu = Menu.create(registMenu.getMenuName(), registMenu.getMenuPrice(), registMenu.getCategoryCode());
     *         menuRepository.save(menu);
     *         return menu.getMenuCode();
     *     }
     *
     *     문제점
     *     1. ① 의 중복 규칙이 modifyMenuName() 에도 복사된다. -> 한쪽만 고치면 등록과 수정의 규칙이 달라진다.
     *     2. ② 에서 Service 가 "refCategoryCode 가 null 이면 상위 카테고리" 라는 Category 의 내부 사정을 알아야 한다.
     *     3. 규칙이 늘어날수록 if 문이 쌓이고, ③ "흐름" 이 묻혀서 이 메서드가 무엇을 하는지 안 보인다.
     *     4. 규칙 하나만 테스트하고 싶어도 Service 전체(Repository 2개)를 준비해야 한다.
     *
     * [After] PART 3 · STEP 12
     *     ① -> MenuNamePolicy
     *     ② -> MenuCategoryPolicy + Category.isSubCategory()
     *     ③ -> Service 에 남는다 (흐름만)
     * ================================================================================================ */
    @Transactional
    public int registNewMenu(MenuDTO registMenu) {
        // TODO 5 Service 계층의 책임은 흐름을 조립하며, Transactional 을 제어한다.

        // 1. 비즈니스 규칙 확인
        // 1-1) 메뉴 등록 시 중복된 메뉴는 등록 X -> DB 를 들려야만 알 수 있다.
        menuNamePolicy.validateNewName(registMenu.getMenuName());
        // 1-2) 메뉴 등록 시 카테고리는 하위 카테고리만 등록 가능하다. -> DB 를 들려야만 알 수 있다.
        menuCategoryPolicy.validateRegistrable(registMenu.getCategoryCode());

        // 2. Entity 객체 생성
        Menu menu = Menu.create(
                registMenu.getMenuName(),
                registMenu.getMenuPrice(),
                registMenu.getCategoryCode()
        );

        // 3. 영속성 컨텍스트에 Entity 객체 Persist
        menuRepository.save(menu);

        return menu.getMenuCode();
    }

    @Transactional
    public void modifyMenuName(int menuCode, String menuName) {
        // TODO 6

        // 1. 수정하고자 하는 엔티티 조회
        Menu foundMenu = menuRepository.findById(menuCode)
                .orElseThrow(() -> new BusinessException(ErrorCode.MENU_NOT_FOUND));

        // 2. 전달받은 새 이름으로 엔티티 이름 변경
        // Entity 객체는 DB 의 한 행이기 때문에 Setter 로 무분별하게 수정하는 것을 지양한다.
        // 변경이 필요할 시 메서드를 통해서 Entity 내부 규칙에 의한 수정을 할 수 있게 한다.
        foundMenu.changeMenuName(menuName);

        /* comment.
        *   영속성 컨텍스트는 변경 감지(Dirty Checking) 기능이 있다.
        *   1. findById 를 하는 순간 PC(영속성 컨텍스트) 는 해당 메뉴 엔티티를 스냅샷 찍어둔다.
        *   2. changeMenuName() 을 진행해서 메뉴 이름을 변경한다.
        *   3. @Transactional 이 걸린 메서드가 종료되면 commit() 이 일어난다.
        *   4. commit() 바로 직전 스냅샷과 엔티티 객체의 변화가 있는지 비교한다.
        * */

    }

    /* ================================================================================================
     * TODO 7 : 메뉴 삭제 - 도메인 규칙(Entity) 과 비즈니스 규칙(Policy) 이 한 유스케이스에 함께 나오는 예
     * ------------------------------------------------------------------------------------------------
     * [Before]
     *     private final OrderMenuRepository orderMenuRepository;   // 다른 팀(Order) 의 Repository 를 직접 주입
     *
     *     @Transactional
     *     public void deleteMenu(int menuCode) {
     *         Menu foundMenu = menuRepository.findById(menuCode).orElseThrow(...);
     *
     *         // ① 도메인 규칙 : Menu 의 현재 상태로 판단 (Service 가 "Y" 라는 내부 표현을 알아야 한다)
     *         if ("Y".equals(foundMenu.getOrderableStatus())) {
     *             throw new BusinessException(ErrorCode.ON_SALE_MENU_CANNOT_DELETE);
     *         }
     *         // ② 비즈니스 규칙 : 다른 도메인(주문) 정보가 필요 (Order 팀 내부 구현에 직접 의존)
     *         if (orderMenuRepository.existsByMenuCode(menuCode)) {
     *             throw new BusinessException(ErrorCode.ORDERED_MENU_CANNOT_DELETE);
     *         }
     *         menuRepository.delete(foundMenu);
     *     }
     *
     * [After]
     *     ① -> Menu.validateDeletable()                                (Entity : 자기 상태)
     *     ② -> MenuDeletionPolicy + MenuOrderHistoryChecker 인터페이스  (Policy : 다른 도메인, 자세한 내용은 Policy 주석)
     *   Service 는 "Y" 가 무엇인지도, Order 팀이 존재하는지도 모른다.
     * ================================================================================================ */
    @Transactional
    public void deleteMenu(int menuCode) {
        // TODO 7 : 메뉴 삭제 시 정책/규칙 처리

        // 사전 준비 Entity 의 상태를 확인하기 위해 find
        Menu found = menuRepository
                .findById(menuCode)
                .orElseThrow(() -> new BusinessException(ErrorCode.MENU_NOT_FOUND));


        // 1. 판매상태가 Y 인 메뉴는 삭제할 수 없다.
        found.validateDeletable();  // 도메인 규칙 (Y/N) 판단

        // 2. 주문된 적이 있는 메뉴는 삭제할 수 없다.
        // 비즈니스 규칙(여러 도메인/DB 의 값을 통해서 판단해야 하는 것들)
        menuDeletionPolicy.validateDeletable(menuCode);

        // 3. 위 규칙과 정책을 통과하면 삭제 진행!
        menuRepository.delete(found);

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
