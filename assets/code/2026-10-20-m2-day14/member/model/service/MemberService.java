package com.eulji.member.model.service;

import com.eulji.global.common.Session;
import com.eulji.global.config.JDBCTemplate;
import com.eulji.member.model.dao.DepartmentDAO;
import com.eulji.member.model.dao.MemberDAO;
import com.eulji.member.model.dto.Department;
import com.eulji.member.model.dto.Member;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MemberService {

    private static final int MIN_PASSWORD_LENGTH = 4;

    private final MemberDAO memberDAO = new MemberDAO();
    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    /* F-02 로그인: 일치하는 계정을 Session에 저장하고 돌려준다 */
    public Member login(String loginId, String password) {
        if (loginId.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다");
        }

        try (Connection con = JDBCTemplate.getConnection()) {
            Member member = memberDAO.findByLogin(con, loginId, password);
            if (member == null) {
                throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다");
            }
            Session.login(member);
            return member;
        } catch (SQLException e) {
            throw new RuntimeException("로그인 처리 중 오류가 발생했습니다", e);
        }
    }

    /* F-02 로그아웃: Session을 비운다 */
    public void logout() {
        Session.logout();
    }

    /* F-01 회원 가입: 학번 중복(R-08) → 학과 존재(R-11) → INSERT */
    public void signUp(Member member) {
        if (member.getLoginId().isBlank() || member.getName().isBlank()) {
            throw new IllegalArgumentException("학번과 이름은 필수입니다");
        }
        validatePassword(member.getPassword());

        try (Connection con = JDBCTemplate.getConnection()) {
            if (memberDAO.existsByLoginId(con, member.getLoginId())) {
                throw new IllegalArgumentException("이미 가입된 학번입니다");
            }
            if (member.getDeptId() == null || !departmentDAO.existsById(con, member.getDeptId())) {
                throw new IllegalArgumentException("없는 학과 번호입니다");
            }
            memberDAO.insert(con, member);
        } catch (SQLException e) {
            throw new RuntimeException("회원 가입 중 오류가 발생했습니다", e);
        }
    }

    /* 가입 화면에서 고를 학과 목록 (F-01, F-06 공용) */
    public List<Department> getDepartments() {
        try (Connection con = JDBCTemplate.getConnection()) {
            return departmentDAO.findAll(con);
        } catch (SQLException e) {
            throw new RuntimeException("학과 조회 중 오류가 발생했습니다", e);
        }
    }

    /* F-03 내 정보 조회: 학과명 포함 */
    public Member getMyInfo() {
        int memberId = Session.getLoginMember().getMemberId();
        try (Connection con = JDBCTemplate.getConnection()) {
            return memberDAO.findById(con, memberId);
        } catch (SQLException e) {
            throw new RuntimeException("내 정보 조회 중 오류가 발생했습니다", e);
        }
    }

    /* F-03 비밀번호 수정: 현재 비밀번호 확인 후 변경 */
    public void changePassword(String currentPassword, String newPassword) {
        Member me = Session.getLoginMember();
        if (!me.getPassword().equals(currentPassword)) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다");
        }
        validatePassword(newPassword);

        try (Connection con = JDBCTemplate.getConnection()) {
            memberDAO.updatePassword(con, me.getMemberId(), newPassword);
            me.setPassword(newPassword);
        } catch (SQLException e) {
            throw new RuntimeException("비밀번호 수정 중 오류가 발생했습니다", e);
        }
    }

    /* F-03 전화번호 수정 */
    public void changePhone(String newPhone) {
        Member me = Session.getLoginMember();
        try (Connection con = JDBCTemplate.getConnection()) {
            memberDAO.updatePhone(con, me.getMemberId(), newPhone);
            me.setPhone(newPhone);
        } catch (SQLException e) {
            throw new RuntimeException("전화번호 수정 중 오류가 발생했습니다", e);
        }
    }

    /* F-04 회원 탈퇴: 비밀번호 확인 → 미반납 확인(R-07) → DELETE → Session 비우기 */
    public void withdraw(String password) {
        Member me = Session.getLoginMember();
        if (!me.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        try (Connection con = JDBCTemplate.getConnection()) {
            int unreturned = memberDAO.countUnreturned(con, me.getMemberId());
            if (unreturned > 0) {
                throw new IllegalArgumentException("반납하지 않은 책이 " + unreturned + "권 있어 탈퇴할 수 없습니다");
            }
            memberDAO.delete(con, me.getMemberId());
            Session.logout();
        } catch (SQLException e) {
            throw new RuntimeException("회원 탈퇴 중 오류가 발생했습니다", e);
        }
    }

    /* F-05 학생 목록·검색: 이름 또는 학과명, 빈 검색어면 전체 */
    public List<Member> searchStudents(String keyword) {
        try (Connection con = JDBCTemplate.getConnection()) {
            return memberDAO.searchStudents(con, keyword);
        } catch (SQLException e) {
            throw new RuntimeException("회원 조회 중 오류가 발생했습니다", e);
        }
    }

    /* F-06 학과 등록: 중복 확인 후 INSERT */
    public void registerDepartment(String deptName) {
        if (deptName.isBlank()) {
            throw new IllegalArgumentException("학과명을 입력해 주세요");
        }
        try (Connection con = JDBCTemplate.getConnection()) {
            if (departmentDAO.existsByName(con, deptName)) {
                throw new IllegalArgumentException("이미 있는 학과입니다");
            }
            departmentDAO.insert(con, deptName);
        } catch (SQLException e) {
            throw new RuntimeException("학과 등록 중 오류가 발생했습니다", e);
        }
    }

    private void validatePassword(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("비밀번호는 4자 이상 입력해 주세요");
        }
    }
}
