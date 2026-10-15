package com.wanted.a_statements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.wanted.common.JDBCTemplate.close;
import static com.wanted.common.JDBCTemplate.getConnection;

public class Application02 {

    public static void main(String[] args) {

        /* 실습 요구사항
         *   Scanner 를 사용해서 메뉴 이름을 전달받아
         *   그 메뉴의 메뉴번호, 메뉴가격, 판매상태를 출력하라.
         *   출력예시 : 날치알스크류바 의 정보 : 1 , 3000 원 , Y
         *  */
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴 이름 입력 : ");
        String menuName = sc.nextLine();

        try {

            stmt = con.createStatement();

            rset = stmt.executeQuery("SELECT MENU_CODE, MENU_PRICE , ORDERABLE_STATUS FROM TBL_MENU WHERE MENU_NAME = '" + menuName + "'");

            if (rset.next()) {
                // 날치알스크류바 의 정보 : 1 , 3000 원 , Y
                System.out.println(
                        menuName + " 의 정보 : " +
                                rset.getString("MENU_CODE") + " , " +
                                rset.getString("MENU_PRICE") + " 원 , " +
                                rset.getString("ORDERABLE_STATUS")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(stmt);
        }

    }

}