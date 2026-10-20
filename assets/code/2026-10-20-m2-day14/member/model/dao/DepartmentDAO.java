package com.eulji.member.model.dao;

import com.eulji.member.model.dto.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    private static final String FIND_ALL = "SELECT dept_id, dept_name FROM TBL_DEPARTMENT ORDER BY dept_id";
    private static final String EXISTS_BY_ID = "SELECT COUNT(*) FROM TBL_DEPARTMENT WHERE dept_id = ?";
    private static final String EXISTS_BY_NAME = "SELECT COUNT(*) FROM TBL_DEPARTMENT WHERE dept_name = ?";
    private static final String INSERT = "INSERT INTO TBL_DEPARTMENT (dept_name) VALUES (?)";

    public List<Department> findAll(Connection con) throws SQLException {
        List<Department> list = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FIND_ALL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Department(rs.getInt("dept_id"), rs.getString("dept_name")));
            }
        }
        return list;
    }

    public boolean existsById(Connection con, int deptId) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(EXISTS_BY_ID)) {
            pstmt.setInt(1, deptId);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public boolean existsByName(Connection con, String deptName) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(EXISTS_BY_NAME)) {
            pstmt.setString(1, deptName);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public int insert(Connection con, String deptName) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT)) {
            pstmt.setString(1, deptName);
            return pstmt.executeUpdate();
        }
    }
}
