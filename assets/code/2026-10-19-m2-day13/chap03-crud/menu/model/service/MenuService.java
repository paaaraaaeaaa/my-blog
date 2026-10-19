package com.wanted.restaurant.menu.model.service;

import com.wanted.restaurant.global.config.JDBCTemplate;
import com.wanted.restaurant.menu.model.dao.MenuDAO;
import com.wanted.restaurant.menu.model.dto.MenuDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MenuService {

    /* comment.
    *   Service 계층의 책임
    *   - Service 계층은 비즈니스 로직을 담당하는 계층이다.
    *   - 비즈니스 로직이란?
    *   - 우리 프로젝트 규칙 상 어떤 작업이 어떤 순서대로
    *   - 처리 되는 지에 대한 정의를 내리는 구문이다.
    *   Service 계층이 해야 할 일
    *   - 1. DAO 계층 호출
    *   - 2. 데이터 검증 및 가공
    *   - 3. 필요 시 여러 DAO 조합(ex- 메뉴 생성 시 카테고리도 같이 삽입)
    *   - 4. 트랜젝션 처리 (commit , rollback) & Connection 생성
    *   - 5. 예외처리
    *  */

    public List<MenuDTO> findAllMenus() {

        // try-with-resource 절
        // try-catch 절은 사용한 자원을 finally 구문에서 수동 해제해야 한다.
        // try() 소괄호 내부 자원을 자동 해제 해준다.
        try (Connection con = JDBCTemplate.getConnection()) {

            return new MenuDAO(con).findAll();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
