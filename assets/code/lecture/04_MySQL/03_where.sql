-- select (조회)
-- SQL 의 90% 이상을 차지하는 문법으로 SQL 의 전부라고도 볼 수 있다.
-- 핵심 2가지
-- 1. 어떤 열을 가져올 것인가? (정보의 종류)
-- 2. 어떤 행을 가져올 것인가? (정보의 범위)

-- where : 어떤 행을 가져올 것인가를 기술하는 조건절이다.

-- 메뉴테이블에서 이름, 가격, 판매상태를 조회해줘. 단, 판매상태가 Y 인 친구들만 조회해줘!
select
	menu_name,
    menu_price,
    orderable_status
from 
	tbl_menu
where
	orderable_status = 'Y';
    
/*
	많이 쓰이는 조건절 비교 연산자
    = : 같다
    !=, <>	: 같지 않다
    > : 크다
    < : 작다
    >= : 크거나 같다
    <= : 작거나 같다
    LIKE : 특정 패턴을 포함한다.
    IN : 목록 안의 값 중 하나와 일치한다.
    BETWEEN : 두 값 사이의 범위에 있다.
	NOT : 부정
*/

-- 메뉴 테이블에서 이름과 가격, 판매상태를 조회해라. 단, 메뉴 가격이 13000원 이하인 메뉴만 조회하라.
select 
	menu_name,
    menu_price,
    orderable_status
from
	tbl_menu
where 
	menu_price <= 13000;
    
-- 메뉴 테이블에서 모든 컬럼을 조회하라. 단, 가격이 10000원 이상, 20000원 미만인 메뉴만 조회하라.
select
	*
from
	tbl_menu
where 
	menu_price >= 10000 
    and 
    menu_price < 20000;
    
-- and, or
-- and 다중 조건을 모두 만족해야 할 때 
-- or 다중 조건 중 1가지라도 만족할 때

-- 메뉴테이블에서 모든 컬럼 조회하라. 단, 판매상태가 Y 이거나 카테고리 코드가 10 인 메뉴를 조회하라. 카테고리 코드로 오름차순 정렬하라.
select
	*
from 
	tbl_menu
where
	orderable_status = 'Y'
    or
    category_code = 10
order by
	category_code asc;
    
-- and로 하면?
select
	*
from 
	tbl_menu
where
	orderable_status = 'Y'
    and
    category_code = 10
order by
	category_code asc;

-- 메뉴 테이블에서 모든 컬럼을 조회하라. 단, 가격이 10000원 과 20000원 사이인 메뉴만 조회하라.
-- between 사용.
select 
	*
from 
	tbl_menu
where 
	menu_price between 10000 and 20000;


-- 메뉴 테이블에서 모든 컬럼을 조회하라. 단, 가격이 10000원 과 20000원 사이가 아닌 메뉴만 조회하라.
select 
	*
from 
	tbl_menu
where 
	menu_price not between 10000 and 20000;
    
-- like 연산자
-- 메뉴 테이블에서 이름과 가격을 조회한다. 단, 메뉴 이름이 '마늘' 을 포함하고 있는 메뉴만 조회한다.
select 
	menu_name, 
    menu_price
from 
	tbl_menu
where 
	-- %단어 : 단어로 끝나는 데이터를 반환
	-- 단어% : 단어로 시작하는 데이터를 반환
    -- %단어% : 단어를 포함하는 데이터를 반환
	menu_name like '생마늘%';
    
-- in 연산자
-- 메뉴테이블에서 이름, 카테고리 조회하라. 단, 카테고리 코드가 4,5,6 이 아닌 메뉴만 조회하라.
select
	menu_name,
    category_code
from 
	tbl_menu
where 
	category_code not in(4,5,6);
    
-- null 처리
-- 카테고리 테이블에서 모든 데이터를 조회한다. 단, null 값을 포함한 데이터는 제외한다.
select 
	*
from
	tbl_category
where 
	ref_category_code is not null;