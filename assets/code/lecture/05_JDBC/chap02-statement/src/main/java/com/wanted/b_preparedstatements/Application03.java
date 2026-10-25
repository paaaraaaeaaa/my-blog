package com.wanted.b_preparedstatements;

import com.wanted.common.MenuDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import static com.wanted.common.JDBCTemplate.close;
import static com.wanted.common.JDBCTemplate.getConnection;

public class Application03 {

    public static void main(String[] args) {


        /* comment.
        *   SQL 구문의 경우 한 문장으로 끝남년 문자열로 작성해도 큰 무리가 없다.
        *   다만, Join 을 하거나 Where 조건절이 길어지게 된다면 문자열 합치기를 통한 SQL 구문 작성이 굉장히 복잡해진다.
        *   ---
        *   XML 파일을 이용한 File I/O 로 SQL 구문 따로 분리하기
        * */

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴 이름 입력 : ");
        String menuName = sc.nextLine();

        // 1개의 DB 행을 MenuDTO 객체에 담기 위한 객체 생성
        MenuDTO menu = null;

        // XML 파일 읽을 준비
        Properties prop = new Properties();

        try {

            // 어떤 파일을 읽어들일지
            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/wanted/b_preparedstatements/menu-query.xml")
            );

            // 읽은 파일에서 어떤 Key 를 가져올 지
            String query = prop.getProperty("findMenuByMenuName");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1,menuName);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                menu = new MenuDTO();
                menu.setMenuCode(rset.getInt("MENU_CODE"));
                menu.setMenuName(rset.getString("MENU_NAME"));
                menu.setMenuPrice(rset.getInt("MENU_PRICE"));
                menu.setCategoryCode(rset.getInt("CATEGORY_CODE"));
                menu.setOrderableStatus(rset.getString("ORDERABLE_STATUS").charAt(0));
            }

            System.out.println("menu = " + menu);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }

    }

}
