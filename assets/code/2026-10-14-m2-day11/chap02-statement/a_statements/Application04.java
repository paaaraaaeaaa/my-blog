package com.wanted.a_statements;

import com.wanted.common.MenuDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.wanted.common.JDBCTemplate.close;
import static com.wanted.common.JDBCTemplate.getConnection;

public class Application04 {

    /* comment. 금일 하부르타 시 과제
    *   1. 메뉴 전체 조회 값을 Java 객체로 옮겨담기
    *   2. EmployeeDB 를 사용해서 각자 1개 이상의 DB 접속 및 조회 결과 객체 옮겨담기
    * */

    public static void main(String[] args) {

        List<MenuDTO> menuList = new ArrayList<>();

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        try {

            stmt = con.createStatement();
            rset = stmt.executeQuery("SELECT * FROM TBL_MENU");

            while (rset.next()) {
                MenuDTO menu = new MenuDTO();
                menu.setMenuCode(rset.getInt("menu_code"));
                menu.setMenuName(rset.getString("menu_name"));
                menu.setMenuPrice(rset.getInt("menu_price"));
                menu.setCategoryCode(rset.getInt("category_code"));
                menu.setOrderableStatus(rset.getString("orderable_status").charAt(0));

                menuList.add(menu);
            }

            for (MenuDTO menu : menuList) {
                System.out.println("menu = " + menu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }

    }

}
