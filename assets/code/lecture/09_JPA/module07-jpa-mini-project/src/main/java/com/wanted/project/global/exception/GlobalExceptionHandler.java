package com.wanted.project.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/*
 * [GlobalExceptionHandler]
 * 역할 : 모든 Controller 에서 발생한 예외를 한 곳에서 받아 에러 화면(error/error) 으로 응답한다.
 * 책임 : - 예외 종류에 맞는 HTTP 상태코드와 사용자에게 보여줄 메시지를 결정한다.
 *        - Controller / Service 에 try-catch 가 흩어지지 않게 한다. (Controller 는 정상 흐름만 작성)
 *        - 예상하지 못한 예외는 로그를 남기고, 내부 정보(스택트레이스)는 화면에 노출하지 않는다.
 *
 * 할 일
 *  0. 이 클래스를 전역 예외 처리기로 등록하기 (@ControllerAdvice)
 *  1. handleBusinessException() : 도메인/비즈니스 규칙 위반 -> ErrorCode 의 상태코드, 메시지
 *  2. handleBindException()  : @Valid DTO 검증 실패 (메뉴 등록)  -> 400, 첫 번째 필드 에러 메시지
 *                              (숫자 칸에 문자/빈 값 -> fieldError.isBindingFailure() 가 true -> "입력 형식" 메시지)
 *  3. handleMethodValidation(): @RequestParam 검증 실패           -> 400, 첫 번째 검증 에러 메시지
 *  4. handleBadRequest()     : 필수 파라미터 누락 / 타입 불일치   -> 400, "요청 값을 확인해주세요."
 *  5. handleDataIntegrity()  : 정책으로 걸러지지 않은 DB 제약조건 위반 (FK 등) -> 409
 *  6. handleNoResource()     : 존재하지 않는 URL                  -> 404
 *  7. handleException()      : 그 외 모든 예외                    -> log.error 후 500
 */
@Slf4j
// 예외처리를 전역으로 핸들링 하기 위해서는
// 해당 어노테이션이 반드시 필요하다.
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ModelAndView handleBusinessException(BusinessException e) {
        // TODO 1
        return errorPage(e.getErrorCode().getStatus(), e.getMessage());
    }

    /* MethodArgumentNotValidException(@Valid @ModelAttribute 검증 실패) 는 BindException 의 자식이다. */
    @ExceptionHandler(BindException.class)
    public ModelAndView handleBindException(BindException e) {
        // TODO 2 : @Vaild 유효성 검사 불통과시 동작하는 핸들러
        // 유효성검사 실패 관련 에러 객체
        FieldError fieldError = e.getFieldError();

        String message = fieldError.isBindingFailure() ?
                fieldError.getField() + "의 형식이 올바르지 않습니다."
                :
                fieldError.getDefaultMessage();
        return errorPage(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ModelAndView handleMethodValidation(HandlerMethodValidationException e) {
        // TODO 3 : @RequestParam 에 직접 붙인 @Min 조건을 만족하지 못하면 동작.
        String message = e.getMessage();
        return errorPage(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    public ModelAndView handleBadRequest(Exception e) {
        // TODO 4
        return null;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrity(DataIntegrityViolationException e) {
        // TODO 5
        return null;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView handleNoResource(NoResourceFoundException e) {
        // TODO 6
        return null;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        // TODO 7
        return null;
    }

    /* 에러 화면(error/error) 에 상태코드와 메시지를 담아 반환한다. (제공 코드) */
    private ModelAndView errorPage(HttpStatus status, String message) {
        ModelAndView mv = new ModelAndView("error/error");
        mv.setStatus(status);
        mv.addObject("status", status.value());
        mv.addObject("message", message);
        return mv;
    }
}
