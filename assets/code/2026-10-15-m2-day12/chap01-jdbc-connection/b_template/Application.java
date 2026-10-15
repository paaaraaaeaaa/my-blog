package com.wanted.b_template;

import java.sql.Connection;

import static com.wanted.b_template.JDBCTemplate.close;
import static com.wanted.b_template.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {

        /* comment.
         *   App01,App02 에서는 DB 접근을 위한
         *   Connection 객체를 만들어보았다.
         *   ---
         *   현재의 문제점.
         *   회원가입, 로그인, 회원정보 수정 , 회원 삭제
         *   - 4번의 DB 접근이 필요하다.
         *   - 접근 시마다 App01 에서 작성한 코드가 반복되어야 한다.
         *   ---
         *   개선 사항
         *   반복되는 코드
         *   - 커넥션 생성
         *   - 프로퍼티 객체 생성
         *   - 커넥션 자원 해제
         *  */

        // static 메서드 호출 방법
        // 클래스명.메서드명();
        Connection con = getConnection();
        System.out.println("con = " + con);
        // con 을 활용해서 회원가입 진행 완료!!

        // 사용한 커넥션 닫기
        close(con);
    }

}