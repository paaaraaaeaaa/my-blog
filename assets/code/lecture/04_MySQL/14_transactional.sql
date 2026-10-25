create table accounts (
	id int primary key,
    owner varchar(50),
    balance decimal(10,2)
);

insert into accounts (id, owner, balance) values(1,'홍길동',10000.00), (2,'김짱구',5000.00);

-- 홍길동 계좌에서 김짱구 계좌로 3000원 이체
-- 작업 순서
-- 1. 홍길동 계좌 -3000
-- 문제 발생!!!!!!!!
-- 2. 김짱구 계좌 +3000
-- 계좌이체는 논리적으로 하나의 작업이지만, 실제로 동작하는 작업은 2번의 업데이트가 발생한다.

-- transaction 이란? 
-- 다수의 작업을 하나의 논리적인 작업 단위로 묶는 것을 의미한다.
-- 따라서 다수의 작업 중 1개의 작업이라도 잘못되면 롤백시키는 것이 핵심이다.

-- start transaction
-- commit
-- rollback

-- 정상 계좌이체 흐름
start transaction;

-- 1번 홍길동 계좌 3000원 차감
update accounts set balance = balance - 3000
where id = 1;

-- 은행 정전

-- 2번 김짱구 계좌 3000원 증액
update accounts set balance = balance + 3000
where id = 2;

-- 중간 확인 / 주의 : 영구 반영된 상태는 아니다.
select * from accounts;

-- 모든 작업이 성공, 반영 시작!
commit;
select * from accounts;

-- 비정상 계좌이체 흐름
start transaction;

-- 1번 홍길동 계좌 3000원 차감
update accounts set balance = balance - 3000
where id = 1;

select * from accounts;

-- 없는 계좌 3000원 증액
update accounts set balance = balance + 3000
where id = 99;

-- 중간 확인 / 주의 : 영구 반영된 상태는 아니다.
select * from accounts;

-- 작업 중 일부가 문제가 발생했기 때문에 돌이킨다!
rollback;