-- =====================================================================
-- 을지대학교 성남캠퍼스 도서 대여 시스템 - sample-data.sql
-- 실행 순서: schema.sql → sample-data.sql
-- 날짜는 CURDATE() 기준이라 언제 실행해도 대여 중·연체 데이터가 유지된다
-- 비밀번호는 모두 1234 (과제 범위상 평문 저장)
-- =====================================================================

USE book_rental;

-- ---------------------------------------------------------------------
-- TBL_DEPARTMENT (학과명은 학교 홈페이지 기준으로 확인 후 수정)
-- ---------------------------------------------------------------------
INSERT INTO TBL_DEPARTMENT (dept_name) VALUES
('간호학과'),          -- 1
('치위생학과'),        -- 2
('안경광학과'),        -- 3
('장례지도학과'),      -- 4
('방사선학과'),        -- 5
('물리치료학과');      -- 6

-- ---------------------------------------------------------------------
-- TBL_MEMBER : 관리자 1명 + 학생 6명
-- ---------------------------------------------------------------------
INSERT INTO TBL_MEMBER (login_id, password, name, phone, role, dept_id) VALUES
('admin',    '1234', '관리자', NULL,            'ADMIN',   NULL),  -- 1
('20230001', '1234', '김민준', '010-1111-0001', 'STUDENT', 1),     -- 2
('20230002', '1234', '이서연', '010-1111-0002', 'STUDENT', 1),     -- 3 (연체 중)
('20230003', '1234', '박도윤', '010-1111-0003', 'STUDENT', 2),     -- 4 (연장 후 연체)
('20240001', '1234', '최하은', '010-1111-0004', 'STUDENT', 3),     -- 5
('20240002', '1234', '정지호', '010-1111-0005', 'STUDENT', 4),     -- 6
('20250001', '1234', '강수아', '010-1111-0006', 'STUDENT', 5);     -- 7 (대여 기록 없음 → 탈퇴 테스트용)

-- ---------------------------------------------------------------------
-- TBL_CATEGORY
-- ---------------------------------------------------------------------
INSERT INTO TBL_CATEGORY (category_name) VALUES
('문학'),          -- 1
('인문'),          -- 2
('사회과학'),      -- 3
('자연과학'),      -- 4
('보건의료'),      -- 5 (도서 없음 → 카테고리 삭제 성공 테스트용)
('자기계발');      -- 6

-- ---------------------------------------------------------------------
-- TBL_BOOK : available_qty = total_qty - (미반납 대여 수)
-- ---------------------------------------------------------------------
INSERT INTO TBL_BOOK (title, author, publisher, pub_year, category_id, total_qty, available_qty) VALUES
('채식주의자',          '한강',                          '창비',         2007, 1, 3, 1),  -- 1 (대여 중 2)
('소년이 온다',         '한강',                          '창비',         2014, 1, 2, 1),  -- 2 (대여 중 1)
('아몬드',              '손원평',                        '창비',         2017, 1, 2, 1),  -- 3 (대여 중 1)
('불편한 편의점',       '김호연',                        '나무옆의자',   2021, 1, 2, 1),  -- 4 (대여 중 1, 연체)
('달러구트 꿈 백화점',  '이미예',                        '팩토리나인',   2020, 1, 1, 0),  -- 5 (재고 0 → 대여 불가 테스트용)
('코스모스',            '칼 세이건',                     '사이언스북스', 2006, 4, 1, 1),  -- 6
('사피엔스',            '유발 하라리',                   '김영사',       2015, 2, 2, 2),  -- 7
('이기적 유전자',       '리처드 도킨스',                 '을유문화사',   2018, 4, 1, 1),  -- 8 (대여 기록 없음 → 삭제 테스트용)
('정의란 무엇인가',     '마이클 샌델',                   '김영사',       2010, 3, 1, 1),  -- 9 (대여 기록 없음)
('미움받을 용기',       '기시미 이치로, 고가 후미타케',  '인플루엔셜',   2014, 6, 2, 1);  -- 10 (대여 중 1)

-- ---------------------------------------------------------------------
-- TBL_RENTAL : 반납 완료 / 대여 중 / 연체 / 연장 사례
--   대여 기간 14일(R-05), 연장 시 +7일
-- ---------------------------------------------------------------------
INSERT INTO TBL_RENTAL (member_id, book_id, rental_date, due_date, return_date, is_extended) VALUES
-- 반납 완료
(2, 1,  CURDATE() - INTERVAL 30 DAY, CURDATE() - INTERVAL 16 DAY, CURDATE() - INTERVAL 20 DAY, FALSE),
(3, 7,  CURDATE() - INTERVAL 40 DAY, CURDATE() - INTERVAL 26 DAY, CURDATE() - INTERVAL 27 DAY, FALSE),
(5, 7,  CURDATE() - INTERVAL 15 DAY, CURDATE() - INTERVAL 1 DAY,  CURDATE() - INTERVAL 2 DAY,  FALSE),
(6, 1,  CURDATE() - INTERVAL 60 DAY, CURDATE() - INTERVAL 46 DAY, CURDATE() - INTERVAL 50 DAY, FALSE),
(6, 6,  CURDATE() - INTERVAL 35 DAY, CURDATE() - INTERVAL 14 DAY, CURDATE() - INTERVAL 15 DAY, TRUE),
-- 대여 중 (정상)
(2, 3,  CURDATE() - INTERVAL 5 DAY,  CURDATE() + INTERVAL 9 DAY,  NULL, FALSE),
(4, 1,  CURDATE() - INTERVAL 10 DAY, CURDATE() + INTERVAL 4 DAY,  NULL, FALSE),
(5, 2,  CURDATE() - INTERVAL 3 DAY,  CURDATE() + INTERVAL 11 DAY, NULL, FALSE),
(2, 5,  CURDATE() - INTERVAL 12 DAY, CURDATE() + INTERVAL 2 DAY,  NULL, FALSE),
(6, 10, CURDATE() - INTERVAL 8 DAY,  CURDATE() + INTERVAL 6 DAY,  NULL, FALSE),
-- 연체 (미반납 + 반납 예정일 지남)
(3, 1,  CURDATE() - INTERVAL 20 DAY, CURDATE() - INTERVAL 6 DAY,  NULL, FALSE),
(4, 4,  CURDATE() - INTERVAL 25 DAY, CURDATE() - INTERVAL 4 DAY,  NULL, TRUE);   -- 연장 후 연체

-- ---------------------------------------------------------------------
-- 확인용 쿼리 (필요할 때 주석 해제)
-- ---------------------------------------------------------------------
-- F-15 연체 목록
-- SELECT m.login_id, m.name, d.dept_name, b.title, r.due_date,
--        DATEDIFF(CURDATE(), r.due_date) AS overdue_days
--   FROM TBL_RENTAL r
--   JOIN TBL_MEMBER m     ON r.member_id = m.member_id
--   JOIN TBL_DEPARTMENT d ON m.dept_id   = d.dept_id
--   JOIN TBL_BOOK b       ON r.book_id   = b.book_id
--  WHERE r.return_date IS NULL AND r.due_date < CURDATE()
--  ORDER BY overdue_days DESC;

-- F-19 카테고리 목록 (카테고리별 도서 수)
-- SELECT c.category_id, c.category_name, COUNT(b.book_id) AS book_count
--   FROM TBL_CATEGORY c
--   LEFT JOIN TBL_BOOK b ON c.category_id = b.category_id
--  GROUP BY c.category_id, c.category_name
--  ORDER BY c.category_id;
