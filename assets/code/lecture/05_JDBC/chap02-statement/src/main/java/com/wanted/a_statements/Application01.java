package com.wanted.a_statements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.wanted.common.JDBCTemplate.close;
import static com.wanted.common.JDBCTemplate.getConnection;

public class Application01 {

    public static void main(String[] args) {

        /* comment.
         *   JDBC 의 핵심 인터페이스 2가지
         *   1. Statement
         *   - SQL 쿼리문을 저장하고 실행할 수 있는 기능을 가진 인터페이스
         *   2. ResultSet
         *   - SQL 쿼리문의 결과를 결과 집합으로 받아올 수 있는 인터페이스
         *  */

        /* 메뉴 전체조회 기능 */
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        try {
            // statement 객체는 마찬가지로
            // 인터페이스이기 때문에 직접 객체 생성 불가.
            // statement 는 connection 객체가 제공하는
            // createStatement() 메서드로 객체 생성이 가능하다.
            stmt = con.createStatement();

            // executeQuery() 메서드는 쿼리문을 전달 받아
            // 실행시키는 메서드다. 또한 반환 타입은 ResultSet 이다.
            rset = stmt.executeQuery("SELECT * FROM TBL_MENU");

            /* comment. next()
             *   ResultSet 결과를 목록화시켜 있으면 true
             *   없으면 false 를 반환한다.
             *  */

            while (rset.next()) {
                System.out.println(
                        rset.getString("MENU_NAME") + " 은/는 "
                                + rset.getString("MENU_PRICE") + "원 입니다. "
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