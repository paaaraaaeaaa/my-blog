package com.wanted.entitymapping.section01;

/* comment. enum(열거형)
*   enum 타입은 시스템 정책 상 정해져 있는 값
*   즉, 제한된 값만 사용하기 위해 강제화 할 수 있는 역할의 클래스이다.
*   ex) 성별, 권한 등등 정해져 있는 값
* */
public enum MemberRole {
    // 우리 프로젝트에서 회원의 권한은 2개로 고정!
    ROLE_MEMBER, ROLE_ADMIN;
}
