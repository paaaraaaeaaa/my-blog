package com.wanted.aop.section02;

import com.wanted.aop.section02.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    /* comment.
    *   주제 : 회원 관리 프로그램
    *   기능 : 회원가입, 회원조회, 비밀번호 수정, 탈퇴
    *   비기능 : 로깅, 성능측정
    *  */

    public static void main(String[] args) {

        System.out.println("==============AOP 적용 후================");

        // IoC 컨테이너 생성
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService service = context.getBean(MemberService.class);

        try {
            System.out.println("[테스트 1] 회원 가입");
            MemberDTO member = new MemberDTO("user@example.com", "pass0123", "홍길동", "010-1111-1111", "USER");
            service.registerMember(member);
            System.out.println("=================");

            System.out.println("[테스트 2] 회원 조회");
            MemberDTO foundMember = service.getMember("user@example.com");
            System.out.println("조회 된 회원 = " + foundMember);
            System.out.println("=================");

            System.out.println("[테스트 3] 비밀번호 변경");
            service.updatePassword("user@example.com", "pass0123", "pass0234");
            System.out.println("=================");

            System.out.println("[테스트 4] 회원 탈퇴");
            service.deleteMember("user@example.com");
            System.out.println("=================");

            // 일부러 예외를 발생
            System.out.println("[테스트 5] 존재하지 않는 회원 조회");
            service.getMember("user@example.com");
            System.out.println("=================");

        } catch (Exception e) {
            System.out.println("예외 발생!! : " + e.getMessage());
        }

        System.out.println("==============AOP 적용 후================");

    }

}
