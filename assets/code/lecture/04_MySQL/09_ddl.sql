-- DDL (Data Definition Language)
-- 데이터를 어떤 식으로 정의하는 지에 대한 언어
-- 데이터베이스의 테이블을 정의하거나, 수정하는데 사용된다.

--
/*
 * 테이블을 만드는 것은 단순히 칸을 나누는 작업이 아니다.
 * 데이터가 지켜야 할 규칙을 정의하고, 데이터베이스가 스스로 데이터의 품질을 유지하도록 만드는 시스템을 설계하는 과정이다.
 * 이러한 규칙이 없다면 데이터베이스에 여러가지 데이터가 저장이 되고 그로 인해 데이터를 조작하거나 관리하는데 어려움이 생길 수 있기 때문이다.
 * 이 규칙을 정의하는 두 가지 핵심 요소가 바로 '데이터 타입'과 '제약 조건'이다.
 *
 * 1. 데이터 타입 (Data Types)은 왜 필요한가?
 * - 각 열(Column)에 어떤 종류의 데이터만 저장될 수 있는지 강제하는 규칙이다.
 * '나이' 열에 '스무살'이라는 글자가 들어가는 것을 원천 차단하여 데이터의 오염을 막고,
 * 데이터 종류에 맞는 최적의 공간만 사용하여 저장 효율을 높인다.
 *
 * - 각 열(Column)에 저장될 데이터의 종류를 지정한다.
 * - INT/BIGINT: 정수(Integer)를 저장한다. (예: user_id, author_id)
 * - VARCHAR(n): 최대 n글자의 변수 길이 문자열(Variable Character)을 저장한다. (예: email, name)
 * - TEXT: 매우 긴 텍스트를 저장한다. (예: description, content)
 * - DATE: 날짜(YYYY-MM-DD)를 저장한다.
 * - DATETIME/TIMESTAMP: 날짜와 시간(YYYY-MM-DD HH:MI:SS)을 함께 저장한다.
 *
 *
 * 2. 제약 조건 (Constraints)은 왜 필요한가? ✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨
 * - 데이터 타입을 넘어, 데이터들 사이의 논리적인 규칙까지 강제하는 강력한 도구이다.
 * '이 칸은 비워둘 수 없음(NOT NULL)', '이 칸의 값은 중복 불가(UNIQUE)',
 * '이 칸의 값은 다른 테이블의 특정 값 중 하나여야 함(FOREIGN KEY)' 등
 * 데이터베이스가 스스로 데이터의 정합성을 지키는 '자동 경찰' 역할을 하도록 만든다.
 *
 * - NOT NULL: 해당 열에 NULL 값이 들어올 수 없도록 강제한다. (필수 입력)
 * - UNIQUE: 해당 열의 모든 값이 테이블 내에서 유일해야 함을 보장한다. (중복 방지)
 * - PRIMARY KEY (기본 키): NOT NULL과 UNIQUE의 특징을 모두 가지는 테이블의 대표 식별자이다.
 * - FOREIGN KEY (외래 키): 다른 테이블의 기본 키를 참조하여 테이블 간의 관계를 맺고 데이터의 일관성을 보장한다.
 * - DEFAULT: 값이 명시적으로 입력되지 않았을 때 사용할 기본값을 지정한다.
 */
-- create : 테이블을 생성하기 위한 구문
-- 컬럼명 / 데이터타입 / 제약조건
create table tb1(
		user_id int primary key,
        user_pwd varchar(12) not null comment '사용자 비밀번호',
        email varchar(30) not null unique comment '이메일',
        user_status varchar(10) default 'active' comment '계정 활성 상태',
        created_at timestamp not null default current_timestamp comment '가입 일시'
) comment '서비스 유저 정보 테이블';

describe tb1;

create table tb2(
		user_id int primary key,
        author_id int not null comment 'tb1 에서 전달받는 외래키',
        user_pwd varchar(12) not null comment '사용자 비밀번호',
        email varchar(30) not null unique comment '이메일',
        user_status varchar(10) default 'active' comment '계정 활성 상태',
        created_at timestamp not null default current_timestamp comment '가입 일시',
        -- ✨ foreign key (`author_id`)
        -- tb2 테이블의 author_id 컬럼을 외래키로 지정한다.
        -- ✨ references `tb1` (`user_id`)
        -- author_id 외래키는 tb1 이라는 부모 테이블의 user_id 컬럼을 참조한다.
        -- 부모 테이블에 존재하지 않는 user_id 를 자식 테이블에 입력을 하려고 하면 데이터베이스가 자동으로 차단한다.
        -- 위 특성이 데이터베이스의 참조 무결성 원칙을 지키는 메커니즘이다.
        foreign key (`author_id`) references `tb1` (`user_id`)
) comment '서비스 유저 정보 테이블2';

describe tb2;

-- tb1 테이블은 tb2 테이블에 user_id 라는 컬럼을 제공하고 있다.
drop table tb1;

drop table tb2;

-- 테이블의 구조 변경하기
-- 미리 만들어진 테이블의 구조를 변경한다.
-- alter table : 이미 만들어진 테이블을 수정하는 명령어
-- drop, truncate : 테이블의 데이터를 제거하는 명령어

-- 임시 테이블 생성
-- courses 테이블을 복제한 temp 테이블 생성
create table courses_temp as select * from courses;
desc courses_temp;

-- price 컬럼을 추가
alter table courses_temp 
add column price int not null default 0;
desc courses_temp;

-- price 컬럼 수정
alter table courses_temp 
modify column price varchar(200) not null default 0;
desc courses_temp;

-- price 컬럼 삭제
alter table courses_temp 
drop column price;
desc courses_temp;

-- 테이블의 데이터와 구조 삭제
-- drop, truncate 
-- drop : 테이블의 데이터와 구조 자체를 영구 삭제한다.
-- 		장점 : 매우 빠르다. 단점 : 롤백이 불가, 신중하게 사용해야 한다.
-- truncate : 테이블의 구조는 남긴다. 데이터만 삭제한다.
select * from courses_temp;

truncate table courses_temp;

drop table courses_temp;