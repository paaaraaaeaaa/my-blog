package com.wanted.b_collection.c_map;

import java.util.Properties;

public class Application02 {
    public static void main(String[] args) {

        /* comment. Properties
        *   .env
        *   DATABASE_URL = ~~~~~~~~~~~~~~~~~~~~~
        *   Key(String) = Value(String)
        *   설정파일을 구성할 때 만드는 파일로서 Map 처럼 Key 와 Value 로 환경설정 값을 저장한다.
        *   단 특징은 Key-Value 모두 String 문자열이다.
        * */

        Properties prop = new Properties();
        prop.setProperty("driver", "cj.jdbc.driver.mysql");
        prop.setProperty("url", "jdbc:mysql://localhost/menudb");
        prop.setProperty("username","wanted");
        prop.setProperty("password","wanted");

        System.out.println("prop = " + prop);

    }
}
