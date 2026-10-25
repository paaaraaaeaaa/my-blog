package com.wanted.b_preparedstatements;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.wanted.common.JDBCTemplate.close;
import static com.wanted.common.JDBCTemplate.getConnection;

public class Application04 {

    public static void main(String[] args) {

        /* comment.
        *   CRUD (Create, Read, Update, Delete)
        *   - READ : 조회 - SELECT
        *   - DML : C, U, D 데이터 조작
        *   SELECT 의 경우 executeQuery() 메서드를 활용해서 조회의 결과를 ResultSet 으로 전달받는다.
        *   DML 의 경우 executeUpdate() 메서드를 활용한다.
        *   ---
        *   DML 은 조회가 아니기 때문에 ResultSet 필요가 없다.
        * */

        Connection con = getConnection();
        PreparedStatement pstmt = null;

        // 가격을 수정할 메뉴 이름을 전달받고 수정할 가격 전달받기.
        Scanner sc = new Scanner(System.in);
        System.out.print("수정 메뉴 이름을 입력해주세요 : ");
        String menuName = sc.nextLine();

        System.out.print("변경할 가격을 입력해주세요 : ");
        int price = sc.nextInt();

        Properties prop = new Properties();

        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/wanted/b_preparedstatements/menu-query.xml"));

            String query = prop.getProperty("modifyMenuPriceByMenuName");

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,price);
            pstmt.setString(2,menuName);

            // C U D 작업을 할 때 사용되는 메서드
            // 반환 타입 int : DML 수행 후 영향을 받은 행의 수를 return
            result = pstmt.executeUpdate();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }

        // Update 가 정상적으로 실행되면 result 변수에는 0 초과 된 값이 들어가고, Update 가 비정상적으로 실행되면 result 변수에는 0 값이 들어가게 된다.

        if(result > 0) {
            System.out.println(menuName + " 메뉴 가격 수정 성공! (영향 받은 행 : )" + result + ")");
        } else {
            System.out.println(menuName + "메뉴 가격 변경 실패! 메뉴 이름을 다시 확인해주세요..");
        }

    }

}
