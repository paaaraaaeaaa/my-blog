package com.wanted.project.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/*
 * [ErrorCode] (제공 코드)
 * 역할 : 비즈니스 예외의 종류를 한 곳에 모아둔 목록. (HTTP 상태코드 + 사용자에게 보여줄 메시지)
 * 책임 : - 예외마다 클래스를 새로 만들지 않고, 상수 하나로 "무슨 규칙을 어겼는지" 표현한다.
 *        - 메시지를 바꿀 때 여기 한 곳만 고친다.
 *        - 규칙을 "판단" 하지 않는다. 판단은 Entity(도메인 규칙) 와 Policy(비즈니스 규칙) 의 책임이다.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* 조회 대상 없음 */
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "메뉴를 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "카테고리를 찾을 수 없습니다."),

    /* 도메인 규칙 위반 (Entity 가 자신의 "현재 상태" 로 판단) */
    ALREADY_STOPPED_MENU(HttpStatus.CONFLICT, "이미 판매중지된 메뉴입니다."),
    ALREADY_ON_SALE_MENU(HttpStatus.CONFLICT, "이미 판매중인 메뉴입니다."),
    ON_SALE_MENU_CANNOT_DELETE(HttpStatus.CONFLICT, "판매중인 메뉴는 삭제할 수 없습니다. 먼저 판매중지해주세요."),

    /* 비즈니스 규칙 위반 (Policy 가 판단) */
    DUPLICATE_MENU_NAME(HttpStatus.CONFLICT, "이미 존재하는 메뉴 이름입니다."),
    NOT_SUB_CATEGORY(HttpStatus.BAD_REQUEST, "메뉴는 하위 카테고리에만 등록할 수 있습니다."),
    ORDERED_MENU_CANNOT_DELETE(HttpStatus.CONFLICT, "주문 이력이 있는 메뉴는 삭제할 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
