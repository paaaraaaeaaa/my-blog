-- grouping
-- 데이터의 그룹을 만들어준다.

-- 우리 서비스에서 총 사용자 수는? 혹은 가장 신고를 많이 당한 사람은?

/*
	집계함수
    count(컬럼명) : 해당 컬럼의 데이터 갯수 세기 (null 제외)
    sum(컬럼명) : 해당 컬럼의 숫자 데이터의 합계 계산
    avg(컬럼명) : 해당 컬럼의 숫자 데이터의 평균 계산
    max(컬럼명) : 해당 컬럼의 숫자 데이터의 최소 계산
    min(컬럼명) : 해당 컬럼의 숫자 데이터의 최대 계산
*/

-- 우리 LMS 전체 사용자 수 조회
select * from users;

select 
	count(*) as '총 사용자 수'
from
	users;
    
-- courses 테이블에 과정을 등록한 교육자가 총 몇 명인지 중복없이 계산 후 조회하라. 
select * from courses;

select 
	count(distinct author_id) as '총 교육자 수'
from
	courses;
    
-- grades 성적 관리 테이블에서 모든 제출물의 총점과 평균 점수를 구하여라. 
select * from grades;

select 
	sum(score) as '총점',
	avg(score) as '평균'
from
	grades;
    
select * from users;
/*
	[에러 발생 케이스]
    비집계 컬럼과 집계함수를 동시에 사용시 발생한다.
    집계가 불가능한 컬럼 (문자열 등) 을 집계함수와 활용하게 되면 Errir 가 발생한다.
    왜?
    어떤 name 값을 count(*) 와 함께 묶어야 할 지 모르기 때문이다.
*/
select
	name,
    count(*) as '총 사용자 수'
from
	users;

-- Group By
-- 테이블의 데이터를 특정 컬럼 기준으로 그룹을 묶고, 각 그룹에 대해 집계 함수를 적용하여 계산하는 기능이다. 

-- status 별 사용자 수를 조회하라!
select
	status,
    count(*) as '사용자 수'
from 
	users
group by
	status;
    
-- course 테이블에서 각 교육자 몇 개의 과정을 개설했는지 조회
-- 조회 시 교육자 번호, 개설 강좌 수 라는 이름으로 조회한다.
select * from courses;

select
	author_id as '교육자 번호',
    count(*) as '개설 강좌 수'
from
	courses
group by 
	author_id;
    
-- having 절
-- having 절은 where 조건절처럼 데이터 조회 조건을 작성할 때 사용한다.
-- 다만, where 조건절과의 차이는 where 는 데이터를 그룹화 하기 전에 필터링한다.
-- having 은 데이터를 그룹화 하고 난 후 필터링을 하게 된다.

-- 개설 강좌 수가 3개 이상인, 교육자 와 그 교육자의 개설 강좌 수를 조회하라.
select
	course_id as '교육자 번호',
    count(*) as '개설 강좌 수'
from
	courses
group by 
	course_id
having
	count(*) >= 3;

-- enrollments 테이블에서 수강 상태가 'completed' 상태인 과정 중 2명 이상의 학생이 등록한 과정(course_id) 과 해당 학생 수를 조회하라. 
select * from enrollments;

select
	course_id,
    count(*) as '학생 수'
from enrollments
where 
	status = 'completed'
group by 
	course_id
having 
	count(*) >= 2;