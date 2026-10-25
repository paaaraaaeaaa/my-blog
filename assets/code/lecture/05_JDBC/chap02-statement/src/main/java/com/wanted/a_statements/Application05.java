package com.wanted.a_statements;

import com.wanted.common.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.wanted.common.JDBCTemplate.close;
import static com.wanted.common.JDBCTemplate.getConnection;

public class Application05 {

    /* comment. 금일 하부르타 시 과제
     *   1. 메뉴 전체 조회 값을 Java 객체로 옮겨담기
     *   2. EmployeeDB 를 사용해서 각자 1개 이상의 DB 접속 및 조회 결과 객체 옮겨담기
     * */

    public static void main(String[] args) {

        List<EmployeeDTO> employeelist = new ArrayList<>();

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;


        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery("SELECT * FROM employeedb.tbl_employee");

            while (rset.next()) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setEmp_id(rset.getString("emp_id"));
                employeeDTO.setEmp_name(rset.getString("emp_name"));
                employeeDTO.setEmp_no(rset.getString("emp_no"));
                employeeDTO.setEmail(rset.getString("email"));
                employeeDTO.setPhone(rset.getString("phone"));
                employeeDTO.setDept_code(rset.getString("dept_code"));
                employeeDTO.setJob_code(rset.getString("job_code"));
                employeeDTO.setSal_level(rset.getString("sal_level"));
                employeeDTO.setSalary(rset.getInt("salary"));
                employeeDTO.setBonus(rset.getFloat("bonus"));
                employeeDTO.setManager_id(rset.getString("manager_id"));
                employeeDTO.setHire_date(rset.getDate("hire_date"));
                employeeDTO.setEnt_date(rset.getDate("ent_date"));
                employeeDTO.setEnt_yn(rset.getString("ent_yn"));

                employeelist.add(employeeDTO);

            }

            for (EmployeeDTO employeeDTO : employeelist) {
                System.out.println("employeeDTO = " + employeeDTO);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }


    }

}
