package com.wanted.restaurant;

import com.wanted.restaurant.menu.controller.MenuController;
import com.wanted.restaurant.menu.model.dao.MenuDAO;
import com.wanted.restaurant.menu.model.service.MenuService;
import com.wanted.restaurant.menu.view.input.MenuInputView;
import com.wanted.restaurant.menu.view.output.MenuOutputView;

public class Application {

    public static void main(String[] args) {

        // 객체 조립 진행
        // 추후 배울 Spring 에서는 Application 이 실행되면
        // 모든 객체가 조립이 된다.
        // 지금은 그 구조를 미리 맛보기 형태로 작성해본다.

        MenuService service = new MenuService();
        MenuController controller = new MenuController(service);
        MenuOutputView outputView = new MenuOutputView();
        MenuInputView inputView = new MenuInputView(controller, outputView);

        inputView.displayMainView();

    }

}
