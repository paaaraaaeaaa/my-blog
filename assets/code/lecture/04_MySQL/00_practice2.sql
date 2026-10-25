-- 문제 1
desc tbl_employee;
desc tbl_department;

select 
	emp_name,
    dept_title
from
	tbl_employee as e
	inner join
    tbl_department as d
    on e.dept_code = d.dept_id;

-- 문제 2
desc tbl_employee;
desc tbl_job;
select 
	emp_name, job_name, salary
from 
	tbl_employee as e inner join tbl_job as j 
    on e.job_code = j.job_code
where j.job_name = "대리";

-- 문제 3
desc tbl_employee;
desc tbl_department;
desc tbl_location;

select
	emp_name, dept_title, local_name
from
	tbl_employee as e 
    inner join
    tbl_department as d
    on e.dept_code = d.dept_id
    inner join
    tbl_location as l
    on
    d.location_id = l.local_code
where l.local_code = 'ASIA1';