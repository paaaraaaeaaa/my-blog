package com.wanted.restaurant.menu.controller;

import com.wanted.restaurant.menu.model.dto.MenuDTO;
import com.wanted.restaurant.menu.model.service.MenuService;

import java.util.List;

public class MenuController {

    /* comment.
    *   Controller 계층의 책임
    *   - View 와 Service 사이를 연결하는 커멘드센터
    *   - View 에서 사용자의 입력 값을 토대로 Controller 에게
    *     전달을 하게 되면 Controller 는 적절한 Service 를
    *     호출한다.
    *   Controller 가 해야할 일
    *   - 1. View 에서 전달 받은 요청을 처리하는 메서드
    *   - 2. 적절한 Service 메서드 호출 코드
    *   - 3. 필요하다면 DTO 로 값 조립, 간단한 유효성 처리
    *   Controller 가 하면 안되는 일
    *   - 1. Scanner 입력 처리 -> view 의 일
    *   - 2. 비즈니스 관련 로직
    *   - 3. SQL 관련 작업
    *  */

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    public List<MenuDTO> findAllMenus() {

        List<MenuDTO> menuList = menuService.findAllMenus();

        return menuList;
    }
}
