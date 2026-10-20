package com.eulji.member.controller;

import com.eulji.member.model.dto.Department;
import com.eulji.member.model.dto.Member;
import com.eulji.member.model.service.MemberService;

import java.util.List;

/* 화면(입력·출력)은 View가 맡고, Controller는 Service 호출만 한다 */
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /* F-02 로그인 */
    public Member login(String loginId, String password) {
        return memberService.login(loginId, password);
    }

    /* F-02 로그아웃 */
    public void logout() {
        memberService.logout();
    }

    /* F-01 회원 가입 */
    public void signUp(Member member) {
        memberService.signUp(member);
    }

    /* F-01, F-06 학과 목록 */
    public List<Department> getDepartments() {
        return memberService.getDepartments();
    }

    /* F-03 내 정보 조회 */
    public Member getMyInfo() {
        return memberService.getMyInfo();
    }

    /* F-03 비밀번호 수정 */
    public void changePassword(String currentPassword, String newPassword) {
        memberService.changePassword(currentPassword, newPassword);
    }

    /* F-03 전화번호 수정 */
    public void changePhone(String newPhone) {
        memberService.changePhone(newPhone);
    }

    /* F-04 회원 탈퇴 */
    public void withdraw(String password) {
        memberService.withdraw(password);
    }

    /* F-05 회원 목록·검색 */
    public List<Member> searchStudents(String keyword) {
        return memberService.searchStudents(keyword);
    }

    /* F-06 학과 등록 */
    public void registerDepartment(String deptName) {
        memberService.registerDepartment(deptName);
    }
}
