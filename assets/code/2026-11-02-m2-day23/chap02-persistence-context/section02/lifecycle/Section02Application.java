package com.wanted.persistencecontext.section02.lifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Section02Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Section02Application.class, args);

        EntityLifeCycle lifeCycle = context.getBean(EntityLifeCycle.class);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("========== section03. 엔티티의 생명주기 ==========");
            System.out.println("1. 비영속 상태 확인");
            System.out.println("2. 영속 상태 확인 (1차 캐시와 동일성 보장)");
            System.out.println("3. 준영속 상태 확인 (detach)");
            System.out.println("4. 삭제 상태 확인 (remove)");
            System.out.println("5. 병합 확인 (merge)");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 번호를 입력하세요 : ");
            String no = sc.nextLine();

            try {
                switch (no) {
                    case "1":
                        lifeCycle.transientState(inputInt(sc, "메뉴 코드 : "));
                        break;
                    case "2":
                        lifeCycle.managedState(inputInt(sc, "메뉴 코드 : "));
                        break;
                    case "3":
                        int managedMenuCode = inputInt(sc, "영속 상태로 둘 메뉴 코드 : ");
                        int detachMenuCode = inputInt(sc, "준영속 상태로 만들 메뉴 코드 : ");
                        lifeCycle.detachedState(managedMenuCode, detachMenuCode);
                        break;
                    case "4":
                        lifeCycle.removedState(inputInt(sc, "삭제할 메뉴 코드 : "));
                        break;
                    case "5":
                        int menuCode = inputInt(sc, "메뉴 코드 : ");
                        System.out.print("변경할 메뉴 이름 : ");
                        String menuName = sc.nextLine();
                        lifeCycle.merge(menuCode, menuName);
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

    private static int inputInt(Scanner sc, String message) {
        System.out.print(message);
        return Integer.parseInt(sc.nextLine());
    }
}