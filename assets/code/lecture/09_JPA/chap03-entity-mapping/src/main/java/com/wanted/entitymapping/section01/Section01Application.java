package com.wanted.entitymapping.section01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;
import java.util.Scanner;

@SpringBootApplication
public class Section01Application {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Section01Application.class, args);

        MemberController memberController = context.getBean(MemberController.class);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("========== section01. 엔티티 매핑 ==========");
            System.out.println("1. 회원 등록");
            System.out.println("2. 회원 번호로 회원 조회");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 번호를 입력하세요 : ");
            String no = sc.nextLine();

            try {
                switch (no) {
                    case "1":
                        memberController.registMember(inputMember(sc));
                        break;
                    case "2":
                        System.out.print("조회할 회원 번호 : ");
                        memberController.findMember(Integer.parseInt(sc.nextLine()));
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

    private static MemberRegistDTO inputMember(Scanner sc) {

        MemberRegistDTO newMember = new MemberRegistDTO();

        System.out.print("아이디(10자 이내) : ");
        newMember.setMemberId(sc.nextLine());
        System.out.print("비밀번호 : ");
        newMember.setMemberPwd(sc.nextLine());
        System.out.print("이름 : ");
        newMember.setMemberName(sc.nextLine());
        System.out.print("전화번호 : ");
        newMember.setPhone(sc.nextLine());
        System.out.print("주소 : ");
        newMember.setAddress(sc.nextLine());
        System.out.print("권한 (1. 일반회원 2. 관리자) : ");
        newMember.setMemberRole("2".equals(sc.nextLine()) ? MemberRole.ROLE_ADMIN : MemberRole.ROLE_MEMBER);

        newMember.setEnrollDate(LocalDateTime.now());
        newMember.setStatus("Y");

        return newMember;
    }
}
