package com.wanted.restaurant.global.common;

import java.io.InputStream;
import java.util.Properties;

public class QueryUtil {

    private static final Properties queries = new Properties();

    // XML에서 쿼리를 로드하는 정적 블록
    static {
        try (InputStream inputStream = QueryUtil.class.getClassLoader().getResourceAsStream("queries.xml")) {
            queries.loadFromXML(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("쿼리 로딩 중 오류 발생", e);
        }
    }

    /**
     * 특정 쿼리 ID로 SQL 가져오기
     * @param key XML에서 정의한 entry의 key
     * @return SQL 쿼리 문자열
     */
    public static String getQuery(String key) {
        return queries.getProperty(key).trim();
    }

}
