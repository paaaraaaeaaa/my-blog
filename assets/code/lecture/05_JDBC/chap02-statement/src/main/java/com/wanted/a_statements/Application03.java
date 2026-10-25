package com.wanted.a_statements;

import com.wanted.common.MenuDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.wanted.common.JDBCTemplate.close;
import static com.wanted.common.JDBCTemplate.getConnection;

public class Application03 {

    public static void main(String[] args) {


        /* comment.
        *   App03 에서 진행하는 내용
        *   - 데이터베이스의 정보를 단순 출력으로 끝내는 것이 아닌 Java 객체로 옮겨 담아서 추가적인 작업을 할 준비를 해보자.
        * */

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴 이름 입력 : ");
        String menuName = sc.nextLine();

        // 1개의 DB 행을 MenuDTO 객체에 담기 위한 객체 생성
        MenuDTO menu = null;

        try {

            stmt = con.createStatement();

            rset = stmt.executeQuery("SELECT * FROM TBL_MENU WHERE MENU_NAME = '" + menuName + "'");

            if (rset.next()) {
                menu = new MenuDTO();
                menu.setMenuCode(rset.getInt("MENU_CODE"));
                menu.getMenuName(rset.getString("MENU_NAME"));
                menu.setMenuPrice(rset.getInt("MENU_PRICE"));
                menu.setCategoryCode(rset.getInt("CATEGORY_CODE"));
                menu.setOrderableStatus(rset.getString("ORDERABLE_STATUS").charAt(0));
            }

            System.out.println("menu = " + menu);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(stmt);
        }

    }

}
