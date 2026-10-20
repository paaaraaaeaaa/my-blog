package com.eulji.member.model.dto;

import java.time.LocalDateTime;

public class Member {

    private int memberId;
    private String loginId;     // 학생은 학번, 관리자는 admin
    private String password;
    private String name;
    private String phone;
    private String role;        // STUDENT / ADMIN
    private Integer deptId;     // 관리자는 NULL
    private String deptName;    // JOIN 조회용
    private LocalDateTime createdAt;
    private int rentalCount;    // 대여 중 권수 (회원 목록 조회용)

    public Member() {
    }

    public int getRentalCount() {
        return rentalCount;
    }

    public void setRentalCount(int rentalCount) {
        this.rentalCount = rentalCount;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Member{memberId=" + memberId + ", loginId='" + loginId + "', name='" + name
                + "', phone='" + phone + "', role='" + role + "', deptId=" + deptId
                + ", deptName='" + deptName + "', createdAt=" + createdAt + "}";
    }
}
