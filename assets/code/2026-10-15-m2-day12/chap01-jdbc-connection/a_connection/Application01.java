package com.wanted.a_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application01 {

    public static void main(String[] args) {

        /* comment.
         *   MySQL DB 접근을 위한 Connection
         *   객체 생성
         *  */

        Connection con = null;

        try {
            // MySQL 을 연결하기 위한 드라이버
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection 객체 생성
            // Connection 객체는 인터페이스이기 때문에
            // 객체를 직접 생성하지 못한다.
            // 따라서 해당 인터페이스를 구현한 DriverManager를
            // 통해서 연결 객체를 만들게 된다.
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/menudb",
                    "wanted",
                    "wanted"
            );

            // 출력 시 값이 나오면 menudb 접근 가능
            System.out.println("con = " + con);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // finally 블럭은 생성 된 자원을 해체하는 것에
            // 많이 사용이 된다.
            con = null;
        }

    }

}