package com.wanted.a_connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application02 {

    public static void main(String[] args) {

        /* comment.
         *   App01 의 문제점.
         *   데이터베이스 접근 관련 정보가
         *   Class 에 그대로 노출이 되어 있다.
         *   따라서 우리는 파일 입출력을 활용해서
         *   주요 민감정보를 숨길 것이다.
         *  */

        Connection con = null;

        // 1. 프로퍼티스 객체 생성
        Properties prop = new Properties();

        try {

            prop.load(new FileReader("src/main/java/com/wanted/a_connection/jdbc-config.properties"));

            System.out.println("prop = " + prop);

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);

            con = DriverManager.getConnection(
                    url, user, password
            );

            // 출력 시 값이 나오면 menudb 접근 가능
            System.out.println("con = " + con);

        }  catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con = null;
        }

    }

}