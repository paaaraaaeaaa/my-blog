package com.eulji.global.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

    private static final Properties props = new Properties();

    static {
        try (InputStream in = JDBCTemplate.class.getClassLoader()
                .getResourceAsStream("db-info.properties")) {
            props.load(in);
        } catch (IOException | NullPointerException e) {
            throw new ExceptionInInitializerError("db-info.properties를 읽을 수 없습니다: " + e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("user"),
                    props.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException("DB 연결 실패", e);
        }
    }

    public static void close(AutoCloseable... resources) {
        for (AutoCloseable r : resources) {
            try {
                if (r != null) r.close();
            } catch (Exception ignored) {
            }
        }
    }
}
