package com.wanted.entitymapping.section01;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "tbl_member") // db의 테이블과 매칭
public class Member {
    /* comment.
     *   @GeneratedValue : PK 값을 자동으로 생성하는 전략
     *   - IDENTITY : PK 생성을 DB 에 위임한다. (MySQL 의 AUTO_INCREMENT)
     *                INSERT 를 실행해야 PK 를 알 수 있으므로 persist() 시점에 바로 INSERT 가 실행된다. (쓰기 지연 X)
     *   - SEQUENCE : DB 의 시퀀스 객체를 사용한다. (Oracle, PostgreSQL 등)
     *                persist() 시점에 시퀀스에서 PK 만 먼저 가져오고, INSERT 는 flush 시점에 실행된다. (쓰기 지연 O)
     *   - TABLE    : 키 생성 전용 테이블을 만들어 사용한다.
     *   - AUTO     : DB 에 맞춰 자동으로 선택한다. (기본값)
     *  */
    @Id
    @Column(name = "member_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private int memberNo;

    public Member(String memberId, String memberPwd, String memberName, String phone, String address, LocalDateTime enrollDate, MemberRole memberRole, String status) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.phone = phone;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }

    /* comment.
     *   @Column 의 주요 속성
     *   - name             : 매핑할 컬럼 이름 (기본값 : 필드 이름)
     *   - nullable         : null 허용 여부 (false -> NOT NULL 제약조건)
     *   - unique           : UNIQUE 제약조건 추가
     *   - columnDefinition : 컬럼 정의(자료형, 기본값 등)를 직접 작성
     *   - length           : 문자열 컬럼의 길이 (기본값 255)
     *   ※ 위 속성들은 JPA 가 테이블을 생성(DDL)할 때 사용 된다.
     *  */
    @Column(name = "member_id", nullable = false,
            unique = true, columnDefinition = "varchar(10)")
    private String memberId;

    @Column(name = "member_pwd", nullable = false)
    private String memberPwd;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "address", length = 900)
    private String address;

    /*
     * 📌 날짜 및 시간 타입 매핑
     *
     * ▶ LocalDate: 날짜만 저장 (yyyy-MM-dd)
     * ▶ LocalDateTime: 날짜 + 시간 저장 (yyyy-MM-dd HH:mm:ss)
     *
     * 🎯 JPA는 Hibernate 5 이상에서 java.time 패키지를 자동 지원함
     */
    @Column(name = "enroll_date")
    private LocalDateTime enrollDate;

    /* comment.
     *   @Enumerated
     *   - enum 타입 매핑 시 사용
     *   - ORDINAL : Enum 타입을 필드 순서로 매핑
     *      - 장점 : 0, 1 이런 식으로 저장되기 때문에 메모리 효율성 좋음
     *      - 단점 : 가독성이 떨어지며 필드 추가, 순서 변경 시 데이터 혼돈
     *   - STRING : Enum 타입을 문자열로 매핑
     *       - 권장하는 방식이다. 실무에서는 Enum 타입을 매핑할때 String 으로 한다.
     *       - 장점 : 가독성이 좋으며, 이름으로 저장하기 때문에 순서 변경 지장 없음
     *       - 단점 : 데이터 크기가 숫자보다 크다. <- 하지만 큰 단점은 아니다.
     *   ※ ORDINAL 로 바꿔서 실행해보고 DDL 과 DB 에 저장되는 값을 비교해보자.
     *   ※ Hibernate 6 + MySQL 에서는 STRING 으로 매핑하면 컬럼이 MySQL 의 enum('ROLE_ADMIN','ROLE_MEMBER') 타입으로 생성된다.
     *     varchar 타입으로 만들고 싶다면 columnDefinition = "varchar(20)" 처럼 직접 지정한다.
     *  */
    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Column(name = "status", columnDefinition = "char(1) default 'Y'")
    private String status;
}
