package com.wanted.restaurant.menu.view.output;

import com.wanted.restaurant.menu.model.dto.MenuDTO;

import java.util.List;

public class MenuOutputView {


    public void printMenus(List<MenuDTO> menuList) {

        System.out.println("메뉴 전체조회 리스트!!");
        for(MenuDTO menu : menuList) {
            System.out.println(menu);
        }

    }
}
