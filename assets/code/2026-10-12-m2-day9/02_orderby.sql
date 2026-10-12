-- order by
-- select 구절과 함께 사용을 하며, 결과 집합을 특정 열이나, 값에 따라서 정렬하는데 사용된다.

-- 메뉴테이블에서 메뉴코드, 이름, 가격을 조회해줘. 단, 메뉴 가격 오름차순으로 정렬해줘.
select 
	menu_code,
    menu_name,
    menu_price
from
	tbl_menu
order by -- asc : 오름차순, desc : 내림차순, 기본값 : asc
	menu_price desc;
    
-- 메뉴 테이블에서 코드, 가격, 코드*가격 을 조회하고 코드*가격 순으로 내립차순 정렬해줘.
select
	menu_code,
    menu_price,
    menu_code * menu_price as `연산결과`
from
	tbl_menu
order by
	`연산결과` desc; 
    
select * from tbl_category;

-- null 이 존재하는 컬럼에 대한 정렬
select 
	*
from 
	tbl_category
order by 
	ref_category_code asc;
    
-- 오름차순 정렬 시 null 최상단
select 
	*
from 
	tbl_category
order by 
	ref_category_code is null asc;
-- is null 오름차순 시 null 값이 뒤로 빠지고 오름차순이 된다.