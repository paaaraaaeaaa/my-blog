package com.wanted.restaurant.menu.view.input;

import com.wanted.restaurant.menu.controller.MenuController;
import com.wanted.restaurant.menu.model.dto.MenuDTO;
import com.wanted.restaurant.menu.view.output.MenuOutputView;


import java.util.List;
import java.util.Scanner;

public class MenuInputView {

    /* comment.
    *   View 패키지의 책임
    *   - View 사용자와 상호작용을 하는 책임을 갖는다.
    *   - InputView 의 할 일
    *   - 사용자가 입력할 수 있는 환경을 제공한다.
    *   - 1. Scanner 객체 사용
    *   - 2. Controller 에게 사용자 입력한 값 제공
    *   - 3. OutputView 를 호출하여 결과 출력 요청
    *   - InputView 가 하면 안 되는 일
    *   - 1. SQL 작성
    *   - 2. 비즈니스 로직 처리 (ex 요리하기 등)
    *   - 3. commit / rollback
    *  */

    private final MenuController menuController;
    private final MenuOutputView menuOutputView;
    private final Scanner sc = new Scanner(System.in);

    // 생성자를 통한 필드 초기화
    public MenuInputView(MenuController menuController, MenuOutputView menuOutputView) {
        this.menuController = menuController;
        this.menuOutputView = menuOutputView;
    }

    public void displayMainView() {

        System.out.println("실습 TEST");
        System.out.println("1. 메뉴 전체 조회");
        System.out.print("메뉴를 입력해주세요 : ");

        int menu = sc.nextInt();

        switch (menu) {
            case 1:
                List<MenuDTO> menuList =  menuController.findAllMenus();
                menuOutputView.printMenus(menuList);
                break;

            case 2:

        }

    }
}
