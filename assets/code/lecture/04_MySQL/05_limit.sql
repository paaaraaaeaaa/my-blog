-- limit 
-- 조회 결과에 개수를 제한하는 기능
-- 수백만건 데이터를 한 번에 조회하면 시스템 상에 큰 부하를 주게 된다. 
-- limit 는 '상위 5개만' , '1페이지에 10갸만' 등으로 웹 앱의 페이징 기능을 구현하는데 필수적인 기능이다.

select * from tbl_menu;

select 
	*
from
	tbl_menu
order by
	menu_price desc
limit 
	5;
    
-- limit [offset, ] row_count
-- offset : 시작할 행의 번호 (인덱스 번호)
select 
	*
from
	tbl_menu
order by
	menu_price desc
limit 
	1,5;