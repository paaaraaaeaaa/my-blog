package com.eulji.member.model.dao;

import com.eulji.member.model.dto.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    // 관리자는 dept_id가 NULL이라 LEFT JOIN
    private static final String SELECT_MEMBER = """
            SELECT m.member_id, m.login_id, m.password, m.name, m.phone,
                   m.role, m.dept_id, d.dept_name, m.created_at
            FROM TBL_MEMBER m
            LEFT JOIN TBL_DEPARTMENT d ON m.dept_id = d.dept_id
            """;

    private static final String FIND_BY_LOGIN = SELECT_MEMBER + " WHERE m.login_id = ? AND m.password = ?";
    private static final String FIND_BY_ID = SELECT_MEMBER + " WHERE m.member_id = ?";

    private static final String EXISTS_BY_LOGIN_ID = "SELECT COUNT(*) FROM TBL_MEMBER WHERE login_id = ?";

    private static final String INSERT = """
            INSERT INTO TBL_MEMBER (login_id, password, name, phone, role, dept_id)
            VALUES (?, ?, ?, ?, 'STUDENT', ?)
            """;

    private static final String UPDATE_PASSWORD = "UPDATE TBL_MEMBER SET password = ? WHERE member_id = ?";
    private static final String UPDATE_PHONE = "UPDATE TBL_MEMBER SET phone = ? WHERE member_id = ?";

    // R-07 반납하지 않은 대여 권수
    private static final String COUNT_UNRETURNED =
            "SELECT COUNT(*) FROM TBL_RENTAL WHERE member_id = ? AND return_date IS NULL";

    // 반납 완료된 대여 기록은 FK ON DELETE CASCADE로 함께 삭제된다
    private static final String DELETE = "DELETE FROM TBL_MEMBER WHERE member_id = ?";

    private static final String SEARCH_STUDENTS = """
            SELECT m.member_id, m.login_id, m.name, m.phone, d.dept_name,
                   COUNT(r.rental_id) AS rental_count
            FROM TBL_MEMBER m
            LEFT JOIN TBL_DEPARTMENT d ON m.dept_id = d.dept_id
            LEFT JOIN TBL_RENTAL r ON r.member_id = m.member_id AND r.return_date IS NULL
            WHERE m.role = 'STUDENT' AND (m.name LIKE ? OR d.dept_name LIKE ?)
            GROUP BY m.member_id, m.login_id, m.name, m.phone, d.dept_name
            ORDER BY m.member_id
            """;

    /* 아이디·비밀번호가 일치하는 회원을 조회한다. 없으면 null */
    public Member findByLogin(Connection con, String loginId, String password) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(FIND_BY_LOGIN)) {
            pstmt.setString(1, loginId);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? toMember(rs) : null;
            }
        }
    }

    /* 회원 번호로 학과명과 함께 조회한다. 없으면 null */
    public Member findById(Connection con, int memberId) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(FIND_BY_ID)) {
            pstmt.setInt(1, memberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? toMember(rs) : null;
            }
        }
    }

    public boolean existsByLoginId(Connection con, String loginId) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(EXISTS_BY_LOGIN_ID)) {
            pstmt.setString(1, loginId);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    /* 학생 계정 저장 (role은 항상 STUDENT) */
    public int insert(Connection con, Member member) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT)) {
            pstmt.setString(1, member.getLoginId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getPhone());
            pstmt.setInt(5, member.getDeptId());
            return pstmt.executeUpdate();
        }
    }

    public int updatePassword(Connection con, int memberId, String password) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_PASSWORD)) {
            pstmt.setString(1, password);
            pstmt.setInt(2, memberId);
            return pstmt.executeUpdate();
        }
    }

    public int updatePhone(Connection con, int memberId, String phone) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_PHONE)) {
            pstmt.setString(1, phone);
            pstmt.setInt(2, memberId);
            return pstmt.executeUpdate();
        }
    }

    public int countUnreturned(Connection con, int memberId) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(COUNT_UNRETURNED)) {
            pstmt.setInt(1, memberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public int delete(Connection con, int memberId) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE)) {
            pstmt.setInt(1, memberId);
            return pstmt.executeUpdate();
        }
    }

    /* 학생을 이름 또는 학과명으로 검색한다 (빈 검색어면 전체). 대여 중 권수 포함 */
    public List<Member> searchStudents(Connection con, String keyword) throws SQLException {
        List<Member> list = new ArrayList<>();
        String like = "%" + keyword + "%";
        try (PreparedStatement pstmt = con.prepareStatement(SEARCH_STUDENTS)) {
            pstmt.setString(1, like);
            pstmt.setString(2, like);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Member member = new Member();
                    member.setMemberId(rs.getInt("member_id"));
                    member.setLoginId(rs.getString("login_id"));
                    member.setName(rs.getString("name"));
                    member.setPhone(rs.getString("phone"));
                    member.setDeptName(rs.getString("dept_name"));
                    member.setRentalCount(rs.getInt("rental_count"));
                    list.add(member);
                }
            }
        }
        return list;
    }

    private Member toMember(ResultSet rs) throws SQLException {
        Member member = new Member();
        member.setMemberId(rs.getInt("member_id"));
        member.setLoginId(rs.getString("login_id"));
        member.setPassword(rs.getString("password"));
        member.setName(rs.getString("name"));
        member.setPhone(rs.getString("phone"));
        member.setRole(rs.getString("role"));
        int deptId = rs.getInt("dept_id");
        member.setDeptId(rs.wasNull() ? null : deptId);
        member.setDeptName(rs.getString("dept_name"));
        Timestamp createdAt = rs.getTimestamp("created_at");
        member.setCreatedAt(createdAt == null ? null : createdAt.toLocalDateTime());
        return member;
    }
}
