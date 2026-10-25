-- 문제 1
select * from employeedb.tbl_employee;

-- 문제 2
select emp_id, emp_name from employeedb.tbl_employee;

-- 문제 3
select * from employeedb.tbl_employee where dept_code = "D9";

-- 문제 4
select emp_id,emp_name,dept_code,salary from employeedb.tbl_employee where salary>=3000000;

-- 문제 5
select emp_name, dept_code,salary from employeedb.tbl_employee where dept_code = "D6" and salary >= 2000000;

-- 문제 6
select emp_id, emp_name, salary, bonus from employeedb.tbl_employee where bonus is null;

-- 문제 7
select emp_id, emp_name, salary, dept_code, job_code from employeedb.tbl_employee where salary between 3500000 and 5500000;
select emp_id, emp_name, salary, dept_code, job_code from employeedb.tbl_employee where salary >= 3500000 and salary <= 5500000;

-- 문제 8
select emp_id, emp_name, hire_date from employeedb.tbl_employee where emp_name like '김%';

-- 문제 9
select emp_name, emp_no, dept_code from employeedb.tbl_employee where emp_name like '%하%';

-- 문제 10
select emp_id, emp_name, phone from employeedb.tbl_employee where phone like '___9%';

-- 문제 11
select emp_id, emp_name, phone from employeedb.tbl_employee where phone like '___9_______';

-- 문제 12
select emp_name, dept_code, salary from employeedb.tbl_employee where dept_code in('D6','D8');

-- 문제 13
select emp_id, emp_name, email from employeedb.tbl_employee where emp_name not like '이%';

-- 문제 14
select emp_name, salary, job_code from employeedb.tbl_employee where job_code = 'J2' and salary >= 2000000 or job_code = 'J7';

-- 문제 15
select emp_name, salary, job_code from employeedb.tbl_employee where job_code in ('J7','J2') and salary >=2000000;