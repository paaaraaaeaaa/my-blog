-- 각 쿼리문의 결과를 합치는 Union
-- 수학의 합집합, 중복된 행은 제거하여 순수한 합집합을 만든다.

-- users 테이블에 active 사용자와, suspended 사용자 모두에게 이메일을 발송하고자 한다. 탈퇴한 deleted 유저는 제외한다.
-- 사용자의 이름, 이메일, 상태를 조회하라!

-- union 은 각각의 쿼리문의 결과로 합치는 역할을 하게 된다.
-- 단, 합칠 때는 조회하려는 컬럼 갯수가 동일해야 한다.
-- 단, 컬럼 순서와 데이터 타입이 호환이 가능해야 한다.

select 
	`name`,
    `email`,
    `status`
from
	users
where
	status = 'active'

union
    
select 
	`name`,
    `email`,
    `status`
from
	users
where
	status = 'suspended';