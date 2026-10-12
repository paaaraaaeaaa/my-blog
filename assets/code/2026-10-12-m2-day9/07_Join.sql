-- Join (굉장히 중요!!!!!!!!!)


-- 실제 데이터베이스는 비슷한 의미를 가진 데이터들이 각각의 테이블에 분리되어 저장이 되어 있다.
-- 따라서 join 은 각각의 테이블에 존재하는 데이터들을 공통으로 포함하는 값을 기준으로 합쳐서 결과 집합으로 만드는 것을 의미하게 된다.

-- desc
desc courses;
desc users;

-- courses 테이블에 있는 author_id 는 users 테이블의 user_id 를 가져온 값이다.
-- 이는 fk 즉 외래키라고 불리우며, 지금은 간단하게 users 테이블에서 courses 테이블과 연결되기 위한 컬럼이라고 생각을 해보자.

-- 과정 제목과 그 과정을 만든 교육자 이름을 조회하라. courses 랑 user 테이블!
select 
	c.title as '과정 제목',
    u.name as '교육자 이름'
from
	courses as c 
join
	users as u
on c.author_id = u.user_id;

-- join 의 종류
-- inner join
-- left join
-- right join
-- self join
-- outer join

-- left join
-- from 테이블 A JOIN 테이블 B
-- null 유무에 따라 left, right 기준이 달라질 수 있다. 
-- 테이블 A 를 기준으로 데이터를 출력하며 테이블 B의 데이터가 NULL 이어도 출력한다.

select * from user_auth_providers;

select
	u.name as '사용자 이름',
    uap.provider as '소셜 로그인 제공자'
from users as u left join user_auth_providers as uap
on u.user_id = uap.user_id;

select
	u.name as '사용자 이름',
    uap.provider as '소셜 로그인 제공자'
from users as u right join user_auth_providers as uap
on u.user_id = uap.user_id;