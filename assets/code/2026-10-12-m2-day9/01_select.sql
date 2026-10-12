-- select 절은 MySQL 의 가장 기본적 명령어로 원하는 데이터를 조회할 수 있는 명령어이다.

-- 메뉴 테이블에서 메뉴 이름을 조회해줘! (아래 구문 작성)

select 			-- 조회해줘!
	menu_name	-- 무엇을? (컬럼명)
from			-- 어디에서?
	tbl_menu;	-- (데이터베이스 테이블명)
    
-- 메뉴 테이블에서 메뉴 코드와 카테고리 코드, 메뉴 가격을 조회해줘
select
	menu_code, category_code, menu_price
from
	tbl_menu;
    
-- 메뉴 테이블에서 모든 컬럼을 다 조회해줘~
select 
   menu_code, menu_name, menu_price, category_code, orderable_status
from 
	tbl_menu;
    
-- * : 애스터리스크 라고 불리우며, 뜻은 모든 열(컬럼)을 의미한다.
select 
   *
from 
	tbl_menu;
    
-- as : Alias (별칭) 부여하기  
-- 메뉴테이블에서 menu_name, menu_price 를 조회해줘. 단, menu_name 은 메뉴이름 / menu_price 는 가격 이렇게 표시해.
select 
	menu_name as '메뉴이름',
    menu_price as '가격'
from
	tbl_menu;
    
-- mysql 제공하는 기본 함수
select now();
-- 문자열 합치기 함수
select concat ('김','예린');