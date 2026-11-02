package com.wanted.persistencecontext.section01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Section01Application {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Section01Application.class, args);

        EntityManagerCRUD crud = context.getBean(EntityManagerCRUD.class);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("========== section02. 엔티티 매니저를 이용한 CRUD ==========");
            System.out.println("1. 메뉴 코드로 메뉴 조회");
            System.out.println("2. 신규 메뉴 등록");
            System.out.println("3. 메뉴 이름 수정");
            System.out.println("4. 메뉴 삭제");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 번호를 입력하세요 : ");
            String no = sc.nextLine();

            try {
                switch (no) {
                    case "1":
                        crud.findMenuByMenuCode(inputInt(sc, "조회할 메뉴 코드 : "));
                        break;
                    case "2":
                        crud.registMenu(inputNewMenu(sc));
                        break;
                    case "3":
                        int menuCode = inputInt(sc, "수정할 메뉴 코드 : ");
                        System.out.print("변경할 메뉴 이름 : ");
                        String menuName = sc.nextLine();
                        crud.modifyMenuName(menuCode, menuName);
                        break;
                    case "4":
                        crud.removeMenu(inputInt(sc, "삭제할 메뉴 코드 : "));
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

    private static Menu inputNewMenu(Scanner sc) {

        Menu newMenu = new Menu();

        System.out.print("메뉴 이름 : ");
        newMenu.setMenuName(sc.nextLine());
        newMenu.setMenuPrice(inputInt(sc, "메뉴 가격 : "));
        newMenu.setCategoryCode(inputInt(sc, "카테고리 코드 (4:한식 5:중식 6:일식 7:퓨전 8:커피 ...) : "));
        System.out.print("주문 가능 여부 (Y/N) : ");
        newMenu.setOrderableStatus(sc.nextLine().toUpperCase());

        return newMenu;
    }

    private static int inputInt(Scanner sc, String message) {
        System.out.print(message);
        return Integer.parseInt(sc.nextLine());
    }
}
