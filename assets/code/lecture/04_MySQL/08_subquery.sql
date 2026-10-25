-- 쿼리문 내부에 또 다른 쿼리문

-- 사용하고자 하는 데이터베이스 use 
use wanted_lms;

-- SQL 기초 강의를 수강하는 학생들의 평균 점수보다 높은 점수를 받은 학생의 이름은 무엇인가?

-- 1. SQL 기초 강의 수강생들의 평균 점수 구하기
-- 2. 평균 점수보다 높은 점수를 받은 학생 조회

-- '홍길동' 사용자와 같은 년도에 가입한 모든 사용자의 이름과 가입일을 조회하라! 
-- 1. '홍길동' 의 가입 년도 파악하기
-- 2. '홍길동' 의 가입 년도에 가입한 사용자의 이름과 가입일을 조회한다.

-- 1번 해결하기
select
	year(created_at)
from
	users
where
	name = '홍길동';
    
-- 2번 해결하기
select
	name, 
    created_at
from
	users;
    
-- where 조건절에 사용하는 서브쿼리
-- 서브쿼리란, 문제의 조건이 충분하지 않을 때 2개 이상의 쿼리문을 조합해서 1개의 쿼리문으로 만드는 것을 의미한다.
select
	name, 
    created_at
from
	users
where 
	year(created_at) = (
select
	year(created_at)
from
	users
where
	name = '홍길동');
    
-- from 절에 작성하는 서브쿼리
-- select 구절의 결과를 마치 하나의 테이블처럼 사용하는 것을 의미하며, 이는 가상 테이블이라고 불린다.
select 
	u.name as '교육자 이름',
    aut.course_count as '교육자 개설 과정 수'
from
	users u
    join
    (
    select 
		author_id,
        count(*) as course_count
	from
		courses
	group by 
		author_id
    ) as aut 
    on u.user_id = aut.author_id
order by `교육자 개설 과정 수` desc;