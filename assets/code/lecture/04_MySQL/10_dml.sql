-- DML (Data Manipulation Language) ☆★☆★☆★☆★☆★☆★☆★☆★ 매우매우매우중요!!!!!!!!
-- 데이터 조작 언어
-- INSERT, UPDATE, DELETE

select * from users;
desc users;

-- insert into 테이블명 (컬럼명...) values (넣을 값...)
insert into users (`email`, `name`) values ('email@gmail.com', '김예린');

-- 에러 케이스 확인 
-- 데이터를 삽입할 때 Error 가 무지막지하게 많이 발생한다. 
-- ★★★이럴 때 확인해야 하는 건 테이블의 제약조건과, 데이터 타입이다!!! ★★★
-- unique 제약조건 위반 : 중복 이메일 불허
insert into users (`email`, `name`) values ('email@gmail.com', '김파래');
-- not null 제약조건 위반 : name 컬럼은 반드시 데이터 삽입 시 데이터가 존재해야 한다.
insert into users (`email`) values ('email2@gmail.com');

-- 예외 케이스 확인
-- 데이터를 삽입할 때 Error 가 무지막지하게 많이 발생한다. 
-- ★★★이럴 때 확인해야 하는 건 테이블의 제약조건과, 데이터 타입이다!!! ★★★
-- 정수를 입력하더라도 DB 가 '' 를 붙여 문자열로 자동 형변환을 해서 insert 해버린다.
-- 예외에 대한 처리를 DB 에서 하는 것이 아닌 front, back 쪽에서 해야 된다.
insert into users (`email`, `name`) values ('email2@gmail.com', 123);

-- 에러 케이스 확인 
-- 데이터를 삽입할 때 Error 가 무지막지하게 많이 발생한다. 
-- ★★★이럴 때 확인해야 하는 건 테이블의 제약조건과, 데이터 타입이다!!! ★★★
-- 외래키 참조 무결성 위반
insert into courses (`author_id`,`title`,`description`) values (1,'SQL 제약조건!','제약조건을 확인해보아요~');
-- users 테이블에 존재하지 않는 999 회원을 author_id 에 집어넣으려고 하고 있기 때문에 외래키 제약 참조 무결성 조건에 위반된다.
-- 고아데이터를 방지하는 역할을 하게 된다.
insert into courses (`author_id`,`title`,`description`) values (999,'SQL 제약조건!','제약조건을 확인해보아요~');

-- Update
-- update 테이블명 set 컬럼명 = 수정할 값;
-- 수정, 삭제 작업에서 가장 필수적으로 생각해야 하는 것은 where 조건절!!!!!
-- where 조건절에는 1개의 행을 식별할 수 있는 유일한 값으로 조건을 거는 것이 권장된다. (pk, unique)
update users set `name` = '김예린';
update users set `name` = '김파래' where `user_id` = 1;
select * from users;

-- delete
-- delete from 테이블명 [where];
delete from users where email = 'email@gmail.com';

select * from users;

-- 에러 케이스
-- user_id 컬럼은 courses 테이블에 author_id 컬럼으로 fk (외래키) 로 주고 있다.
-- fk 를 받는 테이블(자식 테이블) / fk를 주는 테이블 (부모 테이블) 
-- 부모 테이블에 값을 삭제하면 자식 테이블의 데이터가 고아 데이터가 되기 때문에 삭제가 거부된다.
delete from users where user_id = 1;
select * from courses;