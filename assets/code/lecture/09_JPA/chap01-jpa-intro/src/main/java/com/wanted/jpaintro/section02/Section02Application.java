package com.wanted.jpaintro.section02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Section02Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Section02Application.class, args);

        JpaSolution jpaSolution = context.getBean(JpaSolution.class);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("========== section02. JPA 를 사용한 해결 ==========");
            System.out.println("1. 메뉴 코드로 메뉴 조회 (SQL 작성과 매핑을 JPA 가 대신한다)");
            System.out.println("2. 메뉴와 해당 메뉴의 카테고리 조회 (객체 그래프 탐색)");
            System.out.println("3. 같은 메뉴를 두 번 조회해서 비교 (동일성 보장)");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 번호를 입력하세요 : ");
            String no = sc.nextLine();

            try {
                switch (no) {
                    case "1":
                        jpaSolution.findMenu(inputMenuCode(sc));
                        break;
                    case "2":
                        jpaSolution.findMenuAndCategory(inputMenuCode(sc));
                        break;
                    case "3":
                        jpaSolution.compareSameMenu(inputMenuCode(sc));
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
