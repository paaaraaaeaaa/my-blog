package com.wanted.restaurant.global.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

    // 커넥션 풀 없이 DriverManager 로 직접 커넥션을 생성한다.
    // getConnection() 을 호출할 때마다 새로운 물리 연결이 만들어지고,
    // close() 를 호출하면 실제로 DB 와의 연결이 끊어진다.

    private static final Properties prop = new Properties();

    // static 블록은 정적 코드 블록으로서
    // 클래스가 로드 될 때 한 번만 실행된다.
    static {
        try {
            prop.load(JDBCTemplate.class.getClassLoader().getResourceAsStream("db-info.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // DB 와 새로운 커넥션을 맺어 반환하는 메서드
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                prop.getProperty("db.url"),
                prop.getProperty("db.username"),
                prop.getProperty("db.password")
        );
    }

}
