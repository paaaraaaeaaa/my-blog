-- =====================================================================
-- 을지대학교 성남캠퍼스 도서 대여 시스템 - schema.sql
-- 기준 문서: docs/기획서.md (8. 저장할 데이터)
-- 실행: MySQL Workbench에서 전체 실행 (Ctrl+Shift+Enter)
-- 주의: 기존 book_rental DB를 지우고 새로 만든다
-- =====================================================================

DROP DATABASE IF EXISTS book_rental;
CREATE DATABASE book_rental DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE book_rental;

-- ---------------------------------------------------------------------
-- TBL_DEPARTMENT : 성남캠퍼스 학과                       담당: A
-- ---------------------------------------------------------------------
CREATE TABLE TBL_DEPARTMENT (
    dept_id     INT          AUTO_INCREMENT PRIMARY KEY,
    dept_name   VARCHAR(50)  NOT NULL UNIQUE
);

-- ---------------------------------------------------------------------
-- TBL_MEMBER : 학생 + 관리자                              담당: A
--   login_id : 학생은 학번, 관리자는 admin (R-08 중복 불가)
--   dept_id  : 학생은 필수(R-11), 관리자는 NULL
-- ---------------------------------------------------------------------
CREATE TABLE TBL_MEMBER (
    member_id   INT          AUTO_INCREMENT PRIMARY KEY,
    login_id    VARCHAR(20)  NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,
    name        VARCHAR(30)  NOT NULL,
    phone       VARCHAR(20),
    role        ENUM('STUDENT', 'ADMIN') NOT NULL DEFAULT 'STUDENT',
    dept_id     INT          NULL,
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_member_dept
        FOREIGN KEY (dept_id) REFERENCES TBL_DEPARTMENT(dept_id)
        ON DELETE RESTRICT          -- 학생이 있는 학과는 삭제 불가
);

-- ---------------------------------------------------------------------
-- TBL_CATEGORY : 도서 카테고리                           담당: D
-- ---------------------------------------------------------------------
CREATE TABLE TBL_CATEGORY (
    category_id    INT          AUTO_INCREMENT PRIMARY KEY,
    category_name  VARCHAR(50)  NOT NULL UNIQUE      -- R-09 중복 불가
);

-- ---------------------------------------------------------------------
-- TBL_BOOK : 도서 (카테고리 1:N 도서)                     담당: B
-- ---------------------------------------------------------------------
CREATE TABLE TBL_BOOK (
    book_id        INT          AUTO_INCREMENT PRIMARY KEY,
    title          VARCHAR(200) NOT NULL,
    author         VARCHAR(100) NOT NULL,
    publisher      VARCHAR(100),
    pub_year       INT,
    category_id    INT          NOT NULL,             -- R-09 모든 도서는 카테고리 하나에 속함
    total_qty      INT          NOT NULL DEFAULT 1,
    available_qty  INT          NOT NULL DEFAULT 1,
    created_at     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_book_qty
        CHECK (total_qty >= 1 AND available_qty >= 0 AND available_qty <= total_qty),
    CONSTRAINT fk_book_category
        FOREIGN KEY (category_id) REFERENCES TBL_CATEGORY(category_id)
        ON DELETE RESTRICT          -- R-10 도서가 있는 카테고리는 삭제 불가
);

-- ---------------------------------------------------------------------
-- TBL_RENTAL : 대여 (회원 1:N 대여, 도서 1:N 대여)        담당: C
--   return_date : NULL이면 대여 중
--   연체 판단   : return_date IS NULL AND due_date < CURDATE()
-- ---------------------------------------------------------------------
CREATE TABLE TBL_RENTAL (
    rental_id    INT      AUTO_INCREMENT PRIMARY KEY,
    member_id    INT      NOT NULL,
    book_id      INT      NOT NULL,
    rental_date  DATE     NOT NULL,
    due_date     DATE     NOT NULL,                   -- R-05 대여일 + 14일
    return_date  DATE     NULL,
    is_extended  BOOLEAN  NOT NULL DEFAULT FALSE,     -- R-05 연장 1회
    CONSTRAINT fk_rental_member
        FOREIGN KEY (member_id) REFERENCES TBL_MEMBER(member_id)
        ON DELETE CASCADE,          -- R-07 탈퇴 시 반납 완료 기록 함께 삭제
    CONSTRAINT fk_rental_book
        FOREIGN KEY (book_id) REFERENCES TBL_BOOK(book_id)
        ON DELETE RESTRICT          -- R-06 대여 기록이 있는 도서는 삭제 불가
);
