package com.wanted.b_preparedstatements;

import java.sql.*;
import java.util.Scanner;

import static com.wanted.common.JDBCTemplate.close;
import static com.wanted.common.JDBCTemplate.getConnection;

public class Application02 {

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴 이름 입력 : ");
        String menuName = sc.nextLine();

        try {

            /* comment. placeholder
            *   statement 는 sql 구문에 java 변수 삽입 시 불편한 점이 있다.
            *   ex) "'" + menuName + "'" 문자열 입력 시 '' 추가
            *   ? : placeholder
            *   java 변수를 직접 삽입하는 것이 아닌 ? 로 두고 메서드로 채워준다.
            * */
            pstmt = con.prepareStatement("SELECT MENU_CODE, MENU_PRICE , ORDERABLE_STATUS FROM TBL_MENU WHERE MENU_NAME = ?");
            // ? 의 갯수에 따라 parameterIndex 가 결정된다.
            // ? 는 반드시 set메서드로 값을 채워주어야 한다.
            pstmt.setString(1,menuName);

            rset = pstmt.executeQuery();

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
            close(pstmt);
        }

    }

}