package com.wanted.jpaintro.section01.jdbc;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* comment.
 *   JPA 를 사용하지 않고 JDBC 를 직접 사용할 때
 *   발생할 수 있는 문제를 예시로 알아보자.
 *   - Spring 이 만들어 준 DataSource(커넥션 풀)에서 Connection 을 꺼내 사용할 뿐
 *   - SQL 작성, 실행, ResultSet -> 객체 변환은 모두 개발자가 직접 해야 한다.
 *  */
@Component
public class JdbcProblem {

    private final DataSource dataSource;

    public JdbcProblem(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /* comment.
     *   JDBC 를 직접적으로 사용했을 때 발생하는 문제점.
     *   1. 데이터 변환(DTO), SQL 구문 작성, JDBC 코드 중복작성 문제
     *   - Connection, Statement, ResultSet 을 생성하고 반납하는 코드가 조회마다 반복된다.
     *   - ResultSet 의 컬럼 값을 객체의 필드에 하나씩 옮겨 담는 코드도 직접 작성해야 한다.
     *   - 개발 시간 증가, 불필요한 코드 반복, 유지보수성 악화
     *  */
    public void findAllMenu() {

        String query = "SELECT menu_code, menu_name, menu_price, category_code, orderable_status FROM tbl_menu";
        System.out.println("실행 SQL : " + query);

        List<Menu> menuList = new ArrayList<>();

        // try-with-resources : try 블럭이 끝나면 Connection, Statement, ResultSet 을 자동으로 반납한다.
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rset = stmt.executeQuery(query)) {

            while (rset.next()) {
                // 조회 결과(행) 1개를 객체 1개로 직접 옮겨 담는다.
                Menu menu = new Menu();
                menu.setMenuCode(rset.getInt("menu_code"));
                menu.setMenuName(rset.getString("menu_name"));
                menu.setMenuPrice(rset.getInt("menu_price"));
                menu.setCategoryCode(rset.getInt("category_code"));
                menu.setOrderableStatus(rset.getString("orderable_status"));

                menuList.add(menu);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        menuList.forEach(menu -> System.out.println(menu));
    }

    /* comment.
     *   2. SQL 에 의존적인 개발
     *   client 가 요구사항을 변경한다.
     *   -> 메뉴 목록을 조회할 때 카테고리 이름도 같이 보여주세요!
     *   이렇게 요구가 바뀌게 되면
     *   - SQL 구문을 JOIN 구문으로 수정해야 하고
     *   - 카테고리 이름을 담을 수 있는 객체(MenuCategoryDTO)를 새로 만들어야 하며
     *   - ResultSet -> 객체로 옮겨 담는 매핑 코드도 다시 작성해야 한다.
     *   즉, 요구사항이 바뀔 때마다 SQL -> 객체 -> 매핑 코드가 연쇄적으로 수정된다.
     *   객체 중심이 아닌 SQL(테이블) 중심으로 개발하게 되는 것이다.
     *  */
    public void findAllMenuWithCategoryName() {

        String query = "SELECT m.menu_code, m.menu_name, m.menu_price, c.category_name, m.orderable_status " +
                       "FROM tbl_menu m " +
                       "JOIN tbl_category c ON m.category_code = c.category_code " +
                       "ORDER BY m.menu_code";
        System.out.println("실행 SQL : " + query);

        List<MenuCategoryDTO> menuList = new ArrayList<>();

        // 1번 예제와 거의 같은 JDBC 코드를 또 작성해야 한다.
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rset = stmt.executeQuery(query)) {

            while (rset.next()) {
                MenuCategoryDTO menu = new MenuCategoryDTO();
                menu.setMenuCode(rset.getInt("menu_code"));
                menu.setMenuName(rset.getString("menu_name"));
                menu.setMenuPrice(rset.getInt("menu_price"));
                menu.setCategoryName(rset.getString("category_name"));
                menu.setOrderableStatus(rset.getString("orderable_status"));

                menuList.add(menu);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        menuList.forEach(menu -> System.out.println(menu));
    }

    /* comment.
     *   3. 패러다임 불일치 문제(객체지향 관점에서 벗어난다.)
     *   - SQL 관점에서 1:N -> 엔티티 간 연관성을 FK 로 가지고 있는다.
     *     (tbl_menu 는 category_code 라는 "값" 을 가지고 있다.)
     *   - Java 관점에서 1:N -> 연관있는 객체를 자신의 멤버(필드)로 가지게 된다.
     *     (Menu 가 Category 객체를 필드로 가지고 있다면 menu.getCategory() 로 바로 꺼낼 수 있다.)
     *   - 하지만 테이블에 맞춰 만든 Menu 객체는 categoryCode 값만 가지고 있기 때문에
     *   - 카테고리 정보가 필요하면 그 값으로 SQL 을 한 번 더 실행하고 직접 조립해야 한다.
     *  */
    public void findMenuAndCategory(int menuCode) {

        Menu menu = findMenuByMenuCode(menuCode);

        if (menu == null) {
            System.out.println(menuCode + "번 메뉴가 존재하지 않습니다.");
            return;
        }

        System.out.println("조회한 메뉴 = " + menu);

        // menu.getCategory() 처럼 객체를 탐색할 수 없다.
        // categoryCode 값으로 카테고리를 다시 조회해야 한다.
        Category category = findCategoryByCategoryCode(menu.getCategoryCode());

        System.out.println("메뉴의 카테고리 = " + category);
    }

    /* comment.
     *   4. 동일성 보장의 문제
     *  */
    public void compareSameMenu(int menuCode) {

        Menu menu1 = findMenuByMenuCode(menuCode);
        Menu menu2 = findMenuByMenuCode(menuCode);

        if (menu1 == null) {
            System.out.println(menuCode + "번 메뉴가 존재하지 않습니다.");
            return;
        }

        System.out.println("menu1 = " + menu1.hashCode());
        System.out.println("menu2 = " + menu2.hashCode());
        System.out.println("menu1 == menu2 : " + (menu1 == menu2));

        /* comment.
         *   중요!!
         *   하나의 DB 에서 동일한 행에 해당하는 데이터를 꺼냈는데
         *   각기 다른 객체에 담았을 때 발생하는 동일성 보장 문제
         *   -> 한 객체에서 수정을 하게 되면 다른 객체는 수정을 알아차릴 수 없다.
         *  */
        menu1.setMenuName("이름 변경 테스트");

        System.out.println("menu1 의 메뉴 이름만 변경한 후");
        System.out.println("menu1.getMenuName() = " + menu1.getMenuName());
        System.out.println("menu2.getMenuName() = " + menu2.getMenuName());
    }

    // 메뉴 코드로 메뉴 1개 조회 (3, 4번 예제에서 사용)
    private Menu findMenuByMenuCode(int menuCode) {

        String query = "SELECT menu_code, menu_name, menu_price, category_code, orderable_status " +
                       "FROM tbl_menu WHERE menu_code = ?";
        System.out.println("실행 SQL : " + query);

        Menu menu = null;

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, menuCode);

            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    menu = new Menu();
                    menu.setMenuCode(rset.getInt("menu_code"));
                    menu.setMenuName(rset.getString("menu_name"));
                    menu.setMenuPrice(rset.getInt("menu_price"));
                    menu.setCategoryCode(rset.getInt("category_code"));
                    menu.setOrderableStatus(rset.getString("orderable_status"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return menu;
    }

    // 카테고리 코드로 카테고리 1개 조회 (3번 예제에서 사용)
    private Category findCategoryByCategoryCode(int categoryCode) {

        String query = "SELECT category_code, category_name, ref_category_code " +
                       "FROM tbl_category WHERE category_code = ?";
        System.out.println("실행 SQL : " + query);

        Category category = null;

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, categoryCode);

            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    category = new Category();
                    category.setCategoryCode(rset.getInt("category_code"));
                    category.setCategoryName(rset.getString("category_name"));
                    // ref_category_code 는 null 이 허용되기 때문에 getObject 로 꺼내야 한다.
                    category.setRefCategoryCode(rset.getObject("ref_category_code", Integer.class));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return category;
    }
}
