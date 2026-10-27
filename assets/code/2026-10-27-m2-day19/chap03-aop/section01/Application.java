package com.wanted.aop.section01;

public class Application {

    /* comment.
    *   주제 : 회원 관리 프로그램
    *   기능 : 회원가입, 회원조회, 비밀번호 수정, 탈퇴
    *   비기능 : 로깅, 성능측정
    *  */

    public static void main(String[] args) {

        System.out.println("==============AOP 적용 전================");

        MemberService service = new MemberService();

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

        System.out.println("==============AOP 적용 전================");

        /* comment.
        *   section01 의 문제점
        *   1. 코드의 중복
        *   - 모든 메서드 마다 로깅,성능측정,트랙젝션 관리 관련 코드가 반복된다.
        *   2. 낮은 응집도
        *   - 각 메서드가 핵심 비즈니스 로직 외에 다른 코드들을 실행한다.
        *   3. 유지보수의 어려움
        *   - 로그 출력 양식의 변화, 성능 측정 기준치 변화 시
        *   - 여러 메서드 내부에 있는 내용을 전부 수정해야 된다.
        *  */

    }

}
