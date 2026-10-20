package com.eulji.member.view.output;

import com.eulji.member.model.dto.Department;
import com.eulji.member.model.dto.Member;

import java.util.List;

public class MemberOutputView {

    public void printStartMenu() {
        System.out.println();
        System.out.println("===== 을지대 성남캠퍼스 도서 대여 =====");
        System.out.println("1. 로그인");
        System.out.println("2. 회원 가입");
        System.out.println("3. 종료");
    }

    public void printStudentMenu() {
        System.out.println();
        System.out.println("===== 학생 메뉴 =====");
        System.out.println("1. 도서 조회");
        System.out.println("2. 카테고리별 도서");
        System.out.println("3. 대여·반납");
        System.out.println("4. 내 정보");
        System.out.println("5. 로그아웃");
    }

    public void printAdminMenu() {
        System.out.println();
        System.out.println("===== 관리자 메뉴 =====");
        System.out.println("1. 도서 관리");
        System.out.println("2. 카테고리 관리");
        System.out.println("3. 회원 관리");
        System.out.println("4. 연체 목록");
        System.out.println("5. 통계");
        System.out.println("6. 로그아웃");
    }

    public void printNotReady() {
        System.out.println("준비 중인 기능입니다.");
    }

    public void printExit() {
        System.out.println("프로그램을 종료합니다.");
    }

    public void printLoginSuccess(Member member) {
        if ("ADMIN".equals(member.getRole())) {
            System.out.println("관리자 모드로 접속했습니다");
        } else {
            System.out.println(member.getName() + "님 환영합니다");
        }
    }

    public void printLogout() {
        System.out.println("로그아웃되었습니다");
    }

    public void printSignUpSuccess() {
        System.out.println("가입 완료");
    }

    public void printDepartments(List<Department> departments) {
        System.out.println("--- 학과 목록 ---");
        if (departments.isEmpty()) {
            System.out.println("등록된 학과가 없습니다");
            return;
        }
        for (Department d : departments) {
            System.out.println(d.getDeptId() + ". " + d.getDeptName());
        }
    }

    public void printMyInfoMenu() {
        System.out.println();
        System.out.println("===== 내 정보 =====");
        System.out.println("1. 내 정보 조회·수정");
        System.out.println("2. 회원 탈퇴");
        System.out.println("0. 뒤로");
    }

    public void printMyInfo(Member member) {
        System.out.println("--- 내 정보 ---");
        System.out.println("학번: " + member.getLoginId());
        System.out.println("이름: " + member.getName());
        System.out.println("학과: " + member.getDeptName());
        System.out.println("전화번호: " + (member.getPhone() == null ? "" : member.getPhone()));
    }

    public void printEditMenu() {
        System.out.println("수정할 항목: 1. 비밀번호  2. 전화번호  0. 취소");
    }

    public void printEditSuccess() {
        System.out.println("수정 완료");
    }

    public void printWithdrawSuccess() {
        System.out.println("탈퇴 완료");
    }

    public void printWithdrawCancel() {
        System.out.println("탈퇴를 취소했습니다");
    }

    public void printMemberAdminMenu() {
        System.out.println();
        System.out.println("===== 회원 관리 =====");
        System.out.println("1. 회원 목록·검색");
        System.out.println("2. 학과 관리");
        System.out.println("0. 뒤로");
    }

    public void printMemberList(List<Member> members) {
        if (members.isEmpty()) {
            System.out.println("검색 결과가 없습니다");
            return;
        }
        System.out.println("번호 | 학번 | 이름 | 학과 | 대여 중 권수");
        for (Member m : members) {
            System.out.println(m.getMemberId() + " | " + m.getLoginId() + " | " + m.getName()
                    + " | " + m.getDeptName() + " | " + m.getRentalCount() + "권");
        }
    }

    public void printDepartmentMenu() {
        System.out.println();
        System.out.println("===== 학과 관리 =====");
        System.out.println("1. 학과 목록");
        System.out.println("2. 학과 등록");
        System.out.println("0. 뒤로");
    }

    public void printDepartmentRegistered() {
        System.out.println("학과 등록 완료");
    }

    public void printInvalidMenu() {
        System.out.println("없는 메뉴 번호입니다.");
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
