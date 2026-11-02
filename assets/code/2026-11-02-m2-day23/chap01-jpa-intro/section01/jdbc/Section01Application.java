package com.wanted.jpaintro.section01.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

/* comment.
 *   [실행 방법]
 *   - 이 클래스의 main 메서드를 실행하면 콘솔 메뉴가 출력된다.
 *   - SpringApplication.run() 을 호출하면 Spring 컨테이너(ApplicationContext)가 생성되고
 *   - application.yaml 의 DB 접속 정보를 읽어 DataSource(커넥션 풀)를 Bean 으로 등록해준다.
 *   - 또한 @SpringBootApplication 이 붙은 클래스의 패키지(section01.jdbc) 하위만 스캔하기 때문에
 *   - 섹션마다 실행 클래스를 두면 다른 섹션의 클래스와 섞이지 않는다.
 *  */
@SpringBootApplication
public class Section01Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Section01Application.class, args);

        // 컨테이너에 등록 된 Bean 을 꺼내서 사용한다.
        JdbcProblem jdbcProblem = context.getBean(JdbcProblem.class);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("========== section01. JDBC 를 직접 사용할 때의 문제점 ==========");
            System.out.println("1. 메뉴 전체 조회 (반복되는 JDBC 코드와 수작업 매핑)");
            System.out.println("2. 요구사항 변경 : 메뉴 조회 시 카테고리명도 함께 조회 (SQL 에 의존적인 개발)");
            System.out.println("3. 메뉴와 해당 메뉴의 카테고리 조회 (패러다임 불일치)");
            System.out.println("4. 같은 메뉴를 두 번 조회해서 비교 (동일성 보장 문제)");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 번호를 입력하세요 : ");
            String no = sc.nextLine();

            try {
                switch (no) {
                    case "1":
                        jdbcProblem.findAllMenu();
                        break;
                    case "2":
                        jdbcProblem.findAllMenuWithCategoryName();
                        break;
                    case "3":
                        jdbcProblem.findMenuAndCategory(inputMenuCode(sc));
                        break;
                    case "4":
                        jdbcProblem.compareSameMenu(inputMenuCode(sc));
                        break;
                    case "9":
                        System.out.println("프로그램을 종료합니다.");
                        context.close();
                        return;
                    default:
                        System.out.println("잘못 된 번호입니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    private static int inputMenuCode(Scanner sc) {
        System.out.print("메뉴 코드를 입력하세요 : ");
        return Integer.parseInt(sc.nextLine());
    }
}
